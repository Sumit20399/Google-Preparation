package Graphs.bridges;

import java.util.*;

class Solution {
    private int timer = 1;

    // DFS to find the critical connections
    private void dfs(int node, int parent, int[] vis,
                     ArrayList<ArrayList<Integer>> adj, int[] tin, int[] low,
                     List<List<Integer>> bridges) {
        vis[node] = 1;
        tin[node] = low[node] = timer;
        timer++;

        for (Integer it : adj.get(node)) {
            if (it == parent) continue;
            if (vis[it] == 0) {
                // Recur for the node that is not visited yet
                dfs(it, node, vis, adj, tin, low, bridges);
                low[node] = Math.min(low[node], low[it]);

                // If the low value of the child is greater than the tin of the current node, then this edge is a bridge
                if (low[it] > tin[node]) {
                    bridges.add(Arrays.asList(node, it));
                }
            } else {
                // If already visited, update the low value of the current node
                low[node] = Math.min(low[node], tin[it]);
            }
        }
    }

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        // Build the adjacency list
        for (List<Integer> it : connections) {
            int u = it.get(0);
            int v = it.get(1);
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        int[] vis = new int[n];
        int[] tin = new int[n];
        int[] low = new int[n];
        List<List<Integer>> bridges = new ArrayList<>();

        // Perform DFS starting from node 0
        dfs(0, -1, vis, adj, tin, low, bridges);
        return bridges;
    }
}

public class Bridges {
    public static void main(String[] args) {
        int n = 4;
        int[][] edges = {
                {0, 1}, {1, 2},
                {2, 0}, {1, 3}
        };

        // Prepare the connections list
        List<List<Integer>> connections = new ArrayList<>();
        for (int[] edge : edges) {
            connections.add(Arrays.asList(edge[0], edge[1]));
        }

        // Create the Solution object and find the bridges
        Solution obj = new Solution();
        List<List<Integer>> bridges = obj.criticalConnections(n, connections);

        // Print the bridges
        int size = bridges.size();
        for (int i = 0; i < size; i++) {
            int u = bridges.get(i).get(0);
            int v = bridges.get(i).get(1);
            System.out.print("[" + u + ", " + v + "] ");
        }
        System.out.println();
    }
}

