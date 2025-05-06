
# Max Flow Network

This project implements the **Ford-Fulkerson algorithm** using the **Edmonds-Karp** approach (BFS-based) to compute the **maximum flow** in a directed flow network.

## Algorithm Overview
- Uses **Breadth-First Search (BFS)** to find augmenting paths.
- Tracks **residual capacities** to determine bottlenecks.
- Repeats until **no more augmenting paths** exist.
- Measures and outputs performance metrics.

## 📁 Project Structure

```
MaxFlowNetwork/
├── src/
│ ├── FordFulkerson.java # Core algorithm logic
│ ├── Graph.java # Graph structure with edge capacities
│ └── Main.java # Handles input, runs algorithm, outputs results
├── benchmarks/ # Folder for input graph files (.txt)
```


## How to Run

### ✔️ Using IntelliJ IDEA (Recommended)
1. Open the project in IntelliJ.

2. Place your `.txt` input files in the `benchmarks` folder.

3. Right-click `Main.java` and click Run.

### ✔️ Using Terminal
1. Place your `.txt` input files in the `benchmarks` folder.
2. Compile the Java files:
   ```
   javac src/*.java
    ```
3. Run the program: 
```
java -cp src Main
```

## Output
* Augmenting paths and flow added per iteration.
* Final maximum flow.
* Execution time in milliseconds.

## Technologies
* Java
* BFS for path-finding
* Terminal I/O

## Example

```
Running on file: bridge_1.txt
Augmenting path: ( 0->1 ) -> ( 1->5 ) with flow = 1
Current Total Flow: 1
Augmenting path: ( 0->4 ) -> ( 4->5 ) with flow = 1
Current Total Flow: 2
Augmenting path: ( 0->1 ) -> ( 1->2 ) -> ( 2->4 ) -> ( 4->5 ) with flow = 1
Current Total Flow: 3
Augmenting path: ( 0->1 ) -> ( 1->3 ) -> ( 3->4 ) -> ( 4->5 ) with flow = 1
Current Total Flow: 4
Augmenting path: ( 0->1 ) -> ( 1->2 ) -> ( 2->3 ) -> ( 3->4 ) -> ( 4->5 ) with flow = 1
Current Total Flow: 5
Final Maximum Flow: 5
Time taken: 11 ms
```
