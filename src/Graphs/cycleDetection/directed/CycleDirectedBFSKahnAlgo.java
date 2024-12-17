package Graphs.cycleDetection.directed;

import java.util.*;

public class CycleDirectedBFSKahnAlgo {
    // Function to detect cycle in a directed graph
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        int inDegree[] = new int[V]; // Array to store in-degrees of all vertices

        // Calculate in-degrees for all nodes
        for (int i = 0; i < V; i++) {
            for (int node : adj.get(i)) {
                inDegree[node]++;
            }
        }

        Queue<Integer> q = new LinkedList<>(); // Queue for BFS
        // Push all nodes with in-degree = 0 into the queue
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) {
                q.add(i);
            }
        }

        int count = 0; // Count of nodes visited

        // Standard BFS
        while (!q.isEmpty()) {
            int node = q.poll();
            count++; // Increment count for each visited node

            // Reduce the in-degree of all adjacent nodes
            for (int adjacentNode : adj.get(node)) {
                inDegree[adjacentNode]--;
                // If in-degree becomes zero, add it to the queue
                if (inDegree[adjacentNode] == 0) {
                    q.add(adjacentNode);
                }
            }
        }

        // If count of visited nodes is not equal to V, a cycle exists
        return count != V;
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

        CycleDirectedBFSKahnAlgo obj = new CycleDirectedBFSKahnAlgo();
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
    - Calculating the in-degrees takes O(E), where E is the number of edges.
    - BFS traversal visits all vertices and edges, so O(V + E).
    - Total Time Complexity: O(V + E)

    SPACE COMPLEXITY:
    - Space used includes:
        1. In-degree array: O(V)
        2. Queue for BFS: O(V) in the worst case
    - Total Space Complexity: O(V)
*/

