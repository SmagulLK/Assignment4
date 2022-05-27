package com.graphs;

public class Main {
    public static void main(String[] args) {
        WeightedGraph<String> graph = new WeightedGraph<>(true);

        Vertex<String> v1 = new Vertex<>("Almaty");
        Vertex<String> v2 = new Vertex<>("Astana");
        Vertex<String> v3 = new Vertex<>("Shymkent");
        Vertex<String> v4 = new Vertex<>("Kostanay");
        Vertex<String> v5 = new Vertex<>("Kyzylorda");

        graph.addEdge(v1, v2, 2.1);
        graph.addEdge(v1, v3, 7.2);
        graph.addEdge(v3, v2, 3.9);
        graph.addEdge(v2, v4, 3.5);
        graph.addEdge(v3, v5, 5.4);

        System.out.println("Dijkstra:");
        Search<String> djk = new DijkstraSearch<>(graph, v1);
        outputPath(djk, v5);

        System.out.println();
        System.out.println("BFS:");
        Search<String> bfs = new BreadthFirstSearch<>(graph, v1);
        outputPath(bfs, v5);
    }

    public static void outputPath(Search<String> search, Vertex<String> key) {
        for (Vertex<String> vertex : search.pathTo(key)) {
            System.out.print(vertex.getData() + " -> ");
        }
    }
}
