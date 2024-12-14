package Graphs.DSU;

import java.util.HashSet;
import java.util.Set;

public class MakeConnected {
    public static void main(String[] args) {
        int n = 6; // Number of nodes
        int[][] connections = {{1, 2}, {3, 4}, {5, 6}, {1, 5}}; // Given edges

        DisjointSet ds = new DisjointSet(n);
        int cntExtras = 0; // Count extra edges

        // Process each edge
        for (int[] edge : connections) {
            int u = edge[0];
            int v = edge[1];
            if (ds.findUPar(u) == ds.findUPar(v)) {
                cntExtras++; // Edge forms a cycle
            } else {
                ds.unionBySize(u, v); // Union components
            }
        }

        // Count the number of components
        Set<Integer> uniqueComponents = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            uniqueComponents.add(ds.findUPar(i));
        }

        int cntC = uniqueComponents.size(); // Number of components
        int ans = cntC - 1; // Minimum edges required to connect components

        // Check if extra edges are sufficient
        if (cntExtras >= ans) {
            System.out.println("Minimum additional edges required: " + ans);
        } else {
            System.out.println("Impossible to connect all components.");
        }
    }
}
