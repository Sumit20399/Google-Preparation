package Graphs.shortestPath;

import java.util.*;

/*
This algorithm is particularly useful when the graph has negative weight edges
but does not contain negative weight cycles.

But this algorithm is only applicable for directed graphs.
In order to apply this algorithm to an undirected graph,
we just need to convert the undirected edges into directed edges

Time Complexity: O(V.E)
Space Complexity: O(V+E)
 */

public class BellmanFord {
    static class Edge {
        int src, dest, weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    public static void bellmanFord(int vertices, List<Edge> edges, int source) {
        int[] dist = new int[vertices];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        // Relax edges (V - 1) times
        for (int i = 1; i < vertices; i++) {
            for (Edge edge : edges) {
                if (dist[edge.src] != Integer.MAX_VALUE && dist[edge.src] + edge.weight < dist[edge.dest]) {
                    dist[edge.dest] = dist[edge.src] + edge.weight;
                }
            }
        }

        // Check for negative weight cycles
        for (Edge edge : edges) {
            if (dist[edge.src] != Integer.MAX_VALUE && dist[edge.src] + edge.weight < dist[edge.dest]) {
                System.out.println("Graph contains a negative weight cycle");
                return;
            }
        }

        // Print distances
        System.out.println("Vertex\tDistance from Source");
        for (int i = 0; i < vertices; i++) {
            System.out.println(i + "\t" + (dist[i] == Integer.MAX_VALUE ? "Infinity" : dist[i]));
        }
    }

    public static void main(String[] args) {
        int vertices = 5; // Number of vertices
        List<Edge> edges = new ArrayList<>();

        // Add edges (src, dest, weight)
        edges.add(new Edge(0, 1, -1));
        edges.add(new Edge(0, 2, 4));
        edges.add(new Edge(1, 2, 3));
        edges.add(new Edge(1, 3, 2));
        edges.add(new Edge(1, 4, 2));
        edges.add(new Edge(3, 2, 5));
        edges.add(new Edge(3, 1, 1));
        edges.add(new Edge(4, 3, -3));

        int source = 0;
        bellmanFord(vertices, edges, source);
    }
}

