package Graphs.cycleDetection.undirected;

import java.util.*;

// Class to represent a Node storing current vertex and its parent
class Node {
    int first;  // The current node
    int second; // The parent node

    public Node(int first, int second) {
        this.first = first;
        this.second = second;
    }
}

public class CycleUndirectedBFS {
    // Function to check for a cycle starting from a source node using BFS
    static boolean checkForCycle(ArrayList<ArrayList<Integer>> adj, int s, boolean vis[], int parent[]) {
        Queue<Node> q = new LinkedList<>(); // Queue for BFS traversal
        q.add(new Node(s, -1));  // Start with the source node and no parent
        vis[s] = true;

        // Traverse the graph using BFS
        while (!q.isEmpty()) {
            // Extract the current node and its parent
            int node = q.peek().first;
            int par = q.peek().second;
            q.remove();

            // Check all adjacent nodes
            for (Integer it : adj.get(node)) {
                // If the adjacent node is not visited, mark it visited and push into the queue
                if (!vis[it]) {
                    q.add(new Node(it, node));
                    vis[it] = true;
                }
                // If the adjacent node is already visited and is not the parent of the current node,
                // then there is a cycle
                else if (par != it) {
                    return true;
                }
            }
        }
        return false;  // No cycle detected starting from this node
    }

    // Function to detect a cycle in an undirected graph
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean vis[] = new boolean[V]; // Visited array to keep track of visited nodes
        Arrays.fill(vis, false);

        int parent[] = new int[V]; // Parent array to track the parent of each node
        Arrays.fill(parent, -1);

        // Check for cycle in each component of the graph
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                if (checkForCycle(adj, i, vis, parent)) {
                    return true; // Cycle found
                }
            }
        }

        return false; // No cycle found in any component
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

        CycleUndirectedBFS obj = new CycleUndirectedBFS();
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
    - The graph is traversed using BFS, visiting each vertex and its adjacent edges once.
    - Let V be the number of vertices and E be the number of edges.
    - Time Complexity: O(V + E)

    SPACE COMPLEXITY:
    - The space used includes:
        1. Visited array of size O(V).
        2. Parent array of size O(V).
        3. BFS queue which in the worst case can store O(V) nodes.
    - Space Complexity: O(V)

    The adjacency list representation takes O(V + E) space for storing the graph.
*/

