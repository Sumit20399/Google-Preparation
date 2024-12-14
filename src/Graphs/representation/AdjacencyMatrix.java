package Graphs.representation;

public class AdjacencyMatrix {
    public static void main(String[] args) {
        int vertices = 5; // Number of vertices
        int[][] edges = {
                {0, 1},
                {0, 2},
                {1, 2},
                {1, 3},
                {2, 4}
        }; // List of edges

        // Initialize adjacency matrix
        int[][] adjMatrix = new int[vertices][vertices];

        // Fill the adjacency matrix
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            // For an undirected graph
            adjMatrix[u][v] = 1;
            adjMatrix[v][u] = 1;

            // For a directed graph, use only this line:
            // adjMatrix[u][v] = 1;
        }

        // Print the adjacency matrix
        System.out.println("The adjacency matrix is:");
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}

