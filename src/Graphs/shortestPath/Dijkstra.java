package Graphs.shortestPath;

import java.util.*;

/*
Time Complexity: O((V+E)⋅logV)
Space Complexity: O(V+E)
 */

public class Dijkstra {
    static class Node implements Comparable<Node> {
        int vertex;
        int weight;

        Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node other) {
            return this.weight - other.weight;
        }
    }

    public static void dijkstra(int source, List<List<Node>> graph, int n) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(source, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.vertex;

            for (Node neighbor : graph.get(u)) {
                int v = neighbor.vertex;
                int weight = neighbor.weight;

                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.add(new Node(v, dist[v]));
                }
            }
        }

        // Print shortest distances
        System.out.println("Vertex\tDistance from Source");
        for (int i = 0; i < n; i++) {
            System.out.println(i + "\t" + dist[i]);
        }
    }

    public static void main(String[] args) {
        int n = 6; // Number of vertices
        List<List<Node>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // Add edges (u, v, w) to the graph
        graph.get(0).add(new Node(1, 4));
        graph.get(0).add(new Node(2, 4));
        graph.get(1).add(new Node(2, 2));
        graph.get(1).add(new Node(3, 5));
        graph.get(2).add(new Node(3, 8));
        graph.get(2).add(new Node(4, 4));
        graph.get(3).add(new Node(5, 6));
        graph.get(4).add(new Node(5, 1));

        int source = 0;
        dijkstra(source, graph, n);
    }
}

