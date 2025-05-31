package Graphs.DSU;

public class CountComponents {
    public int countComponents(int n, int[][] edges) {
        int[] parent = new int[n];
        int count = n;

        for (int i = 0; i < n; i++) parent[i] = i;

        for (int[] edge : edges) {
            int x = find(edge[0], parent);
            int y = find(edge[1], parent);
            if (x != y) {
                parent[x] = y;
                count--;  // Merged two components
            }
        }

        return count;
    }

    private int find(int x, int[] parent) {
        if (parent[x] != x)
            parent[x] = find(parent[x], parent);
        // Path compression
        return parent[x];
    }
}

