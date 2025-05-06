/*
    Purpose: This file implements the Ford-Fulkerson algorithm to compute the maximum flow in a flow network.
    Calculates the maximum flow from a source node to a sink node in the flow network.
    Implementation:
    Uses Breadth-First Search (BFS) to find augmenting paths with available capacity (Edmonds-Karp variant).
    Increases the flow along each found path until no more augmenting paths exist.
    Outputs each augmenting path found, the flow added, and the final maximum flow with execution time.
 */

import java.util.*;

//  FordFulkerson class to perform maximum flow calculations
public class FordFulkerson {

    // Helper method to perform BFS and find augmenting path
    boolean bfs(Graph graph, int source, int sink, int[] parent){
        boolean[] visited = new boolean[graph.numberOfNodes]; // Track visited node
        Queue<Integer> queue = new LinkedList<>(); // Queue for BFS traversal
        queue.add(source); // Start from the source node
        visited[source] = true; // Mark source as visited
        parent[source] = -1; // Source has no parent

        while(!queue.isEmpty()){ // Continue until there are no more nodes to process
            int u = queue.poll(); // Removes the front node from the queue

            for (int v=0; v < graph.numberOfNodes; v++){ // Explore all possible next nodes
                if (!visited[v] && graph.capacity[u][v] > 0){  // If not visited and capacity available
                    queue.add(v); // Add to queue
                    parent[v] = u; // Set parent of v to u
                    visited[v] = true; // Mark v as visited
                }
            }
        }
        return visited[sink]; // Return true if sink is reached (path exists)
    }

    // Main method to compute the maximum floe from source to sink
    public int maxFlow(Graph graph, int source, int sink ){

        int maxFlow = 0; // Initialize maximum flow to zero
        int[] parent = new int[graph.numberOfNodes]; // Array to store the path

        long startTime = System.nanoTime(); // Start timer ( for performance measurement )

        while (bfs(graph, source, sink, parent)){ // While there is an augmenting path
            int pathFlow = Integer.MAX_VALUE; // Start with infinite path flow
            List<String> path = new ArrayList<>(); // List to record the current augmenting path

            // Trace the path from sink to source using parent array
            for(int v = sink; v != source; v = parent[v]){
                int u = parent[v];
                pathFlow = Math.min(pathFlow, graph.capacity[u][v]); // Find minimum capacity in the path
                path.add("( " + u + "->" + v + " )"); // Record the step
            }

            Collections.reverse(path); // reverse path to show source to sink order

            // Print the augmenting path and flow
            System.out.println("Augmenting path: " + String.join(" -> ", path) + " with flow = " + pathFlow);
            System.out.println("Current Total Flow: " + ( maxFlow + pathFlow ));

            // Update residual capacities along the path
            for (int v = sink; v != source; v = parent[v]){
                int u = parent[v];
                graph.capacity[u][v] -= pathFlow; // Reduce capacity on forward edge
                graph.capacity[v][u] += pathFlow; // Increase capacity on backward edge
            }

            maxFlow += pathFlow; // Add path flow to overall flow

        }

        long endTime = System.nanoTime(); // End timer
        long durationMs = (endTime - startTime) / 1_000_000; // Calculate elapsed time in milliseconds

        // Print final results
        System.out.println("Final Maximum Flow: " + maxFlow);
        System.out.println("Time taken: " + durationMs + " ms");

        return maxFlow; // return the final maximum flow
    }
}
