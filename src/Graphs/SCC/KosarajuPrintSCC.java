package Graphs.SCC;

import java.util.*;

class Helper {
    private void dfs(int node, int[] vis, ArrayList<ArrayList<Integer>> adj, Stack<Integer> st) {
        vis[node] = 1;
        for (Integer it : adj.get(node)) {
            if (vis[it] == 0) {
                dfs(it, vis, adj, st);
            }
        }
        st.push(node); // Push the node after all its neighbors are visited
    }

    private void dfsOnTranspose(int node, int[] vis, ArrayList<ArrayList<Integer>> adjT, List<Integer> scc) {
        vis[node] = 1;
        scc.add(node); // Add the node to the current SCC
        for (Integer it : adjT.get(node)) {
            if (vis[it] == 0) {
                dfsOnTranspose(it, vis, adjT, scc);
            }
        }
    }

    // Function to find the SCCs in the graph.
    public List<List<Integer>> kosaraju(int V, ArrayList<ArrayList<Integer>> adj) {
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
        List<List<Integer>> allSCCs = new ArrayList<>();
        while (!st.isEmpty()) {
            int node = st.pop();
            if (vis[node] == 0) {
                List<Integer> scc = new ArrayList<>();
                dfsOnTranspose(node, vis, adjT, scc);
                allSCCs.add(scc); // Add the SCC to the list of SCCs
            }
        }

        return allSCCs;
    }
}

public class KosarajuPrintSCC {
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

        Helper obj = new Helper();
        List<List<Integer>> sccs = obj.kosaraju(V, adj);

        // Print the SCCs
        System.out.println("The strongly connected components are:");
        for (List<Integer> scc : sccs) {
            System.out.println(scc);
        }
    }
}

