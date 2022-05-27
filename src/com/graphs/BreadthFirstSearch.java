package com.graphs;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch<T> extends Search<T> {
    public BreadthFirstSearch(WeightedGraph<T> graph, Vertex<T> source) {
        super(source);
        bfs(graph, source);
    }

    private void bfs(WeightedGraph<T> graph, Vertex<T> vertex) {
        Queue<Vertex<T>> queue = new LinkedList<>();
        queue.add(vertex);
        marked.add(vertex);

        while (!queue.isEmpty()) {
            Vertex<T> temporary = queue.remove();

            for (Vertex<T> commonVertex : graph.adjacencyList(temporary)) {
                if (!marked.contains(commonVertex)) {
                    marked.add(commonVertex);
                    edgeTo.put(commonVertex, temporary);
                    queue.add(commonVertex);
                }
            }
        }
    }
}
