package Graphs.bfs;

import java.util.*;

/*
Time Complexity: O(V + E)
Space Complexity: O(V + E)
*/
public class BFS {
    public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> bfs = new ArrayList<>();
        boolean[] vis = new boolean[V];
        Queue<Integer> q = new LinkedList<>();

        // Start BFS from node 0
        q.add(0);
        vis[0] = true;

        while (!q.isEmpty()) {
            int node = q.poll();
            bfs.add(node);

            // Explore all neighbors of the dequeued vertex
            for (Integer neighbor : adj.get(node)) {
                if (!vis[neighbor]) {
                    vis[neighbor] = true;
                    q.add(neighbor);
                }
            }
        }

        return bfs;
    }

    public static void main(String[] args) {
        // Number of vertices in the graph
        int V = 5;

        // Creating adjacency list
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Adding edges to the graph
        adj.get(0).add(1);
        adj.get(1).add(0);
        adj.get(0).add(4);
        adj.get(4).add(0);
        adj.get(1).add(2);
        adj.get(2).add(1);
        adj.get(1).add(3);
        adj.get(3).add(1);

        // Creating an object of BFSGraph and performing BFS
        BFS bfsGraph = new BFS();
        ArrayList<Integer> result = bfsGraph.bfsOfGraph(V, adj);

        // Printing the BFS traversal result
        for (int node : result) {
            System.out.print(node + " ");
        }
    }
}

