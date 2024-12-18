package Graphs.topoSort;

import java.util.*;

public class TopoSortBFS {
    // Function to return a list containing vertices in Topological order
    public int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) {
        int inDegree[] = new int[V]; // Array to store in-degrees of all vertices

        // Step 1: Calculate in-degrees for all nodes
        for (int i = 0; i < V; i++) {
            for (int node : adj.get(i)) {
                inDegree[node]++;
            }
        }

        Queue<Integer> q = new LinkedList<>(); // Queue for BFS
        // Step 2: Push all nodes with in-degree = 0 into the queue
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) {
                q.add(i);
            }
        }

        int topo[] = new int[V]; // Array to store the topological order
        int index = 0;           // Index for storing in the result array

        // Step 3: Standard BFS traversal
        while (!q.isEmpty()) {
            int node = q.poll();
            topo[index++] = node; // Store the node in topological order

            // Reduce the in-degree of all adjacent nodes
            for (int adjacentNode : adj.get(node)) {
                inDegree[adjacentNode]--;
                // If in-degree becomes zero, add it to the queue
                if (inDegree[adjacentNode] == 0) {
                    q.add(adjacentNode);
                }
            }
        }

        // If the topological order contains all vertices, return the result
        if (index == V) {
            return topo;
        } else {
            throw new IllegalArgumentException("Cycle detected: Topological sorting not possible.");
        }
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        int V = 6; // Number of vertices

        // Initialize adjacency list
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Add directed edges to the graph
        adj.get(5).add(0);
        adj.get(5).add(2);
        adj.get(4).add(0);
        adj.get(4).add(1);
        adj.get(2).add(3);
        adj.get(3).add(1);

        TopoSortBFS obj = new TopoSortBFS();
        try {
            int[] ans = obj.topoSort(V, adj);
            System.out.println("Topological Sort: ");
            for (int node : ans) {
                System.out.print(node + " ");
            }
            System.out.println();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}

/*
    TIME COMPLEXITY:
    - Calculating the in-degrees takes O(E), where E is the number of edges.
    - BFS traversal visits all vertices and edges, so O(V + E).
    - Total Time Complexity: O(V + E), where:
        - V = number of vertices
        - E = number of edges

    SPACE COMPLEXITY:
    - Space used includes:
        1. In-degree array: O(V)
        2. Queue for BFS: O(V) in the worst case
        3. Topological order result array: O(V)
    - Total Space Complexity: O(V)
*/
