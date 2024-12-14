package Graphs.SCC;

import java.util.*;

class Solution {
    private void dfs(int node, int[] vis, ArrayList<ArrayList<Integer>> adj, Stack<Integer> st) {
        vis[node] = 1;
        for (Integer it : adj.get(node)) {
            if (vis[it] == 0) {
                dfs(it, vis, adj, st);
            }
        }
        st.push(node); // Push the node after all its neighbors are visited
    }

    private void dfsOnTranspose(int node, int[] vis, ArrayList<ArrayList<Integer>> adjT) {
        vis[node] = 1;
        for (Integer it : adjT.get(node)) {
            if (vis[it] == 0) {
                dfsOnTranspose(it, vis, adjT);
            }
        }
    }

    // Function to find the number of strongly connected components in the graph.
    public int kosaraju(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] vis = new int[V];
        Stack<Integer> st = new Stack<>();

        // Step 1: Perform DFS to get nodes in order of their finish times
        for (int i = 0; i < V; i++) {
            if (vis[i] == 0) {
                dfs(i, vis, adj, st);
            }
        }

        // Step 2: Create the transpose graph
        ArrayList<ArrayList<Integer>> adjT = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjT.add(new ArrayList<>());
        }
        for (int i = 0; i < V; i++) {
            for (Integer it : adj.get(i)) {
                adjT.get(it).add(i); // Reverse the edge direction
            }
        }

        // Step 3: Perform DFS on the transpose graph in the order of the stack
        Arrays.fill(vis, 0); // Reset the visited array
        int scc = 0; // Count of strongly connected components
        while (!st.isEmpty()) {
            int node = st.pop();
            if (vis[node] == 0) {
                scc++; // Increment SCC count for each DFS call
                dfsOnTranspose(node, vis, adjT);
            }
        }

        return scc;
    }
}

public class KosarajuCountSCC {
    public static void main(String[] args) {
        int V = 5; // Number of vertices
        int[][] edges = {
                {1, 0}, {0, 2},
                {2, 1}, {0, 3},
                {3, 4}
        };

        // Build the adjacency list
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
        }

        Solution obj = new Solution();
        int ans = obj.kosaraju(V, adj);
        System.out.println("The number of strongly connected components is: " + ans);
    }
}

