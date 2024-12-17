package Graphs.topoSort;

import java.util.*;

class TopoSortDFS {
    // Helper function to perform DFS and store nodes in the stack
    private static void dfs(int node, int vis[], Stack<Integer> st,
                            ArrayList<ArrayList<Integer>> adj) {
        vis[node] = 1; // Mark the current node as visited

        // Visit all the adjacent nodes of the current node
        for (int it : adj.get(node)) {
            if (vis[it] == 0) {
                dfs(it, vis, st, adj); // Recur for unvisited nodes
            }
        }

        // Push the current node to the stack after exploring all its adjacent nodes
        st.push(node);
    }

    // Function to return an array containing vertices in Topological order
    static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) {
        int vis[] = new int[V]; // Visited array to track visited nodes
        Stack<Integer> st = new Stack<Integer>(); // Stack to store the topological sort order

        // Perform DFS for all unvisited nodes
        for (int i = 0; i < V; i++) {
            if (vis[i] == 0) {
                dfs(i, vis, st, adj);
            }
        }

        int ans[] = new int[V]; // Array to store the result
        int i = 0;

        // Pop all elements from the stack to get the topological order
        while (!st.isEmpty()) {
            ans[i++] = st.peek();
            st.pop();
        }

        return ans;
    }

    public static void main(String[] args) {
        int V = 6; // Number of vertices
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        // Initialize adjacency list
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Add directed edges to the graph
        adj.get(2).add(3);
        adj.get(3).add(1);
        adj.get(4).add(0);
        adj.get(4).add(1);
        adj.get(5).add(0);
        adj.get(5).add(2);

        // Call the topological sort function
        int[] ans = TopoSortDFS.topoSort(V, adj);

        // Print the result
        for (int node : ans) {
            System.out.print(node + " ");
        }
        System.out.println("");
    }
}

/*
    TIME COMPLEXITY:
    - The graph is represented using an adjacency list.
    - Each vertex is visited exactly once during the DFS traversal.
    - For each vertex, we explore all its adjacent nodes (edges).
    - Therefore, the time complexity is O(V + E), where:
        - V = number of vertices
        - E = number of edges

    SPACE COMPLEXITY:
    - Recursion stack space for DFS in the worst case is O(V), where V is the number of vertices.
    - The `visited` array takes O(V) space.
    - The `stack` used to store the topological order takes O(V) space.
    - Total Space Complexity: O(V)
*/

