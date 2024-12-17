package Graphs.cycleDetection.directed;

import java.util.*;

public class CycleDirectedDFS {
    // Helper function to detect cycle using DFS
    private boolean dfs(int node, int vis[], int pathVis[], ArrayList<ArrayList<Integer>> adj) {
        vis[node] = 1;       // Mark the node as visited
        pathVis[node] = 1;   // Mark the node as part of the current path

        // Traverse all adjacent nodes
        for (int adjacentNode : adj.get(node)) {
            // If the node is not visited, perform DFS
            if (vis[adjacentNode] == 0) {
                if (dfs(adjacentNode, vis, pathVis, adj)) {
                    return true; // Cycle detected
                }
            }
            // If the node is already visited and part of the current path
            else if (pathVis[adjacentNode] == 1) {
                return true; // Cycle found
            }
        }

        pathVis[node] = 0; // Unmark the node as we backtrack
        return false;
    }

    // Function to detect a cycle in a directed graph
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        int vis[] = new int[V];       // Visited array
        int pathVis[] = new int[V];   // Recursion stack array

        // Check for cycles in all components
        for (int i = 0; i < V; i++) {
            if (vis[i] == 0) { // If the node is not visited
                if (dfs(i, vis, pathVis, adj)) {
                    return true; // Cycle detected
                }
            }
        }
        return false; // No cycle detected
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        int V = 4; // Number of vertices

        // Initialize adjacency list
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Add directed edges to the graph
        adj.get(0).add(1);
        adj.get(1).add(2);
        adj.get(2).add(3);
        adj.get(3).add(1); // This edge introduces a cycle

        CycleDirectedDFS obj = new CycleDirectedDFS();
        boolean ans = obj.isCycle(V, adj);

        if (ans) {
            System.out.println("Cycle Detected");
        } else {
            System.out.println("No Cycle Detected");
        }
    }
}

/*
    TIME COMPLEXITY:
    - Each vertex and its edges are visited once.
    - Let V be the number of vertices and E be the number of edges.
    - Time Complexity: O(V + E)

    SPACE COMPLEXITY:
    - Space used includes:
        1. Visited array: O(V)
        2. Recursion stack array: O(V)
        3. DFS recursion call stack: O(V) in the worst case
    - Space Complexity: O(V)
*/

