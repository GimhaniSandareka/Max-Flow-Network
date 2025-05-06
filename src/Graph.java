/*
* Student ID : w2051781/ 20230828
*  name: W. A. Gimhani Sandareka
*/
/*
    Purpose:
    This file defines the Graph data structure used to model the directed
    flow network using a 2D array to store edge capacities.
    Each directed edge has an integer capacity from one node to another.
    Implementation:
    Stores number of nodes.
    Provides methods to add edges, check for existing edges, get the edge capacity, and remove edges.
    Used by the Ford-Fulkerson algorithm for max flow computation
 */

import java.util.*;

//Graph class to represent the network
public class Graph {
    int numberOfNodes; // no of nodes in the graph
    int [][] capacity; // 2D array to store capacities: capacity[from][to] represents the remaining capacity

    // Constructor: Initializes graph with n nodes and zero capacities
    public Graph(int n) {
        this.numberOfNodes = n;
        capacity = new int[n][n]; // Creates an n x n capacity matrix initialized to 0
    }

    public void addEdge(int from, int to, int cap) { // Method to add an edge with a  specific capacity
        capacity[from][to] = cap; // Set the capacity from "from" to "to" node
    }
    public int getCapacity(int from, int to) { // Method to get the capacity between two nodes ('from' node to 'to' node)
        return capacity[from][to]; // Return the value from the matrix
    }
    public void removeEdge(int from, int to) { // Method to remove an edge by setting capacity to 0
        capacity[from][to] = 0; // Removes the edge
    }
    public boolean hasEdge(int from, int to) { // Method to check if an edge exists ( capacity > 0)
        return capacity[from][to] > 0; // Return true if capacity is greater than 0
    }
}