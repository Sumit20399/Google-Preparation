package Graphs.cycleDetection.undirected;

import java.util.*;

public class CycleUndirectedDFS {
    // Helper function to perform DFS and check for a cycle
    private boolean dfs(int node, int parent, int vis[], ArrayList<ArrayList<Integer>> adj) {
        vis[node] = 1; // Mark the current node as visited

        // Traverse all adjacent nodes
        for (int adjacentNode : adj.get(node)) {
            // If the adjacent node is not visited, perform DFS
            if (vis[adjacentNode] == 0) {
                if (dfs(adjacentNode, node, vis, adj)) {
                    return true; // If a cycle is detected in recursion
                }
            }
            // If the adjacent node is visited and is not the parent of the current node,
            // then a cycle is detected
            else if (adjacentNode != parent) {
                return true;
            }
        }
        return false; // No cycle detected from the current node
    }

    // Function to detect a cycle in an undirected graph
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        int vis[] = new int[V]; // Visited array to track visited nodes

        // Check all components of the graph
        for (int i = 0; i < V; i++) {
            if (vis[i] == 0) { // If the node is not visited
                if (dfs(i, -1, vis, adj)) {
                    return true; // Cycle detected
                }
            }
        }
        return false; // No cycle found
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        int V = 4; // Number of vertices

        // Initialize adjacency list
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Add edges to the graph
        adj.get(1).add(2);
        adj.get(2).add(1);
        adj.get(2).add(3);
        adj.get(3).add(2);

        CycleUndirectedDFS obj = new CycleUndirectedDFS();
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
    - DFS visits each vertex and edge once.
    - Let V be the number of vertices and E be the number of edges.
    - Time Complexity: O(V + E)

    SPACE COMPLEXITY:
    - The space used includes:
        1. Visited array of size O(V).
        2. Recursion stack in DFS, which can go up to O(V) in the worst case for a linear graph.
    - Space Complexity: O(V)
*/

