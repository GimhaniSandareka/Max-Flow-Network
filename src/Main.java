/*
    This file controls the whole execution flow.
    Purpose: Main class to read benchmark files, build graphs,
    run Ford-Fulkerson algorithms to compute maximum flow, and output results.
    Implementation:
    Reads all .txt files from "benchmarks" folder and sorts them numerically.
    Processes small files first, large files after
    For each file: parses graph, runs Ford-Fulkerson, outputs results
 */
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // a File object pointing to the benchmark folder
        File folder = new File("benchmarks");

        // get all files inside the folder
        File[] listOfFiles = folder.listFiles();

        // If no files found, print error and exit
        if (listOfFiles == null) { // Check if the folder doesn't exist or empty
            System.out.println("Benchmark folder not found!"); //print the error message
            return; //exit the program
        }

        //Sort files numerically based on their filename (ex: bridge_1, bridge_2)
        Arrays.sort(listOfFiles, (f1, f2) -> {
            String name1 = f1.getName().replaceAll("\\D+", ""); // Remove all non-digit characters
            String name2 = f2.getName().replaceAll("\\D+", "");
            if (name1.isEmpty() || name2.isEmpty()) {  // If no numbers found, fallback to normal name comparison
                return f1.getName().compareTo(f2.getName());
            }
            return Integer.compare(Integer.parseInt(name1), Integer.parseInt(name2)); // Compare by extracted numbers
        });

        // Creates two lists: one for small files, one for large files
        List<File> smallFiles = new ArrayList<>(); // (<=1MB)
        List<File> largeFiles = new ArrayList<>(); // (>1MB)

        //Categorize files into small and large based on the size
        for (File file : listOfFiles) { // loop through all files
            if (file.isFile() && file.getName().endsWith(".txt")) { // Only process .txt files
                if (file.length() <= 1_000_000) { // File size <= 1MB
                    smallFiles.add(file); // Add to small files list
                } else {
                    largeFiles.add(file); // Add to large files list
                }
            }
        }

                System.out.println("\n-- Running SMALL files first ---"); // Indicates the start of small file processing
                for (File file : smallFiles) { // process each small file
                    processFile(file);
                }

                System.out.println("\n-- Now running LARGE files ---"); // Indicate start of large file processing
                for (File file : largeFiles) { // process each large file
                    processFile(file);
                }
            }

            // Method to process a single benchmark file
            private static void processFile(File file){
                System.out.println("\n==========="); // separator
                System.out.println("\nRunning on file: " + file.getName()); // Print which file is being processed

                try(Scanner sc = new Scanner(file)){  // Open the file using Scanner
                    if (!sc.hasNextInt()) { // Check if file is empty or starts incorrectly (if starts with an integer -> number of nodes)
                        System.out.println("Error: File " + file.getName() + " is empty or contains wrong format!.");
                        return; // Skip this file
                    }
                    int n = sc.nextInt(); //  number of nodes

                    if (n > 10000) { // Too large graph size, not allowed
                        System.out.println("Skipping file " + file.getName() + " because it contains too many nodes ( " + n + " ).");
                        return; // skip this file
                    }

                    Graph graph = new Graph(n); // Create a new Graph with n nodes

                    // All edges: from, to, capacity
                    while (sc.hasNextInt()) {  // While there are more integers to read
                        if(sc.hasNextInt()) {
                            int from = sc.nextInt(); // source node
                            if ( !sc.hasNextInt()) {
                                break; // Exit if unexpected end
                            }
                            int to = sc.nextInt(); // destination node
                            if (!sc.hasNextInt()) {
                                break;  // Exit if unexpected end
                            }
                            int cap = sc.nextInt(); // capacity
                            graph.addEdge(from, to, cap); // Add the edge to the graph
                        } else {
                            sc.next(); // skip any invalid tokens
                        }
                    }

                    // Create a FordFulkerson object
                    FordFulkerson ff = new FordFulkerson();
                    ff.maxFlow(graph, 0, n-1); // Compute maximum flow from node 0 (source) to node n-1 (sink)

                } catch (Exception e) { // catch any exceptions during file handling
                    System.out.println("Error reading file: " + file.getName() + ": " + e.getMessage()); // print error message
                }
    }
}
