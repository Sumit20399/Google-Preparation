package Graphs.representation;

import java.util.*;

public class AdjacencyList {
    public static void main(String[] args) {
        int vertices = 5; // Number of vertices
        int[][] edges = {
                {0, 1},
                {0, 2},
                {1, 2},
                {1, 3},
                {2, 4}
        }; // List of edges

        // Initialize adjacency list
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

        // Create empty adjacency list for each vertex
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }

        // Fill the adjacency list
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            // For an undirected graph
            adjList.get(u).add(v);
            adjList.get(v).add(u);

            // For a directed graph, use only this line:
            // adjList.get(u).add(v);
        }

        // Print the adjacency list
        System.out.println("The adjacency list is:");
        for (int i = 0; i < adjList.size(); i++) {
            System.out.print(i + ": ");
            for (int neighbor : adjList.get(i)) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }
}

