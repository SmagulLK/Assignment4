package com.graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DijkstraSearch<T> extends Search<T> {
    private final Set<Vertex<T>> unsettledNodes;
    private final Map<Vertex<T>, Double> distances;
    private final WeightedGraph<T> graph;

    public DijkstraSearch(WeightedGraph<T> graph, Vertex<T> source) {
        super(source);
        unsettledNodes = new HashSet<>();
        distances = new HashMap<>();
        this.graph = graph;
        dijkstra();
    }

    public void dijkstra() {
        distances.put(source, 0D);
        unsettledNodes.add(source);

        while (unsettledNodes.size() > 0) {
            Vertex<T> minVertex = getVertexWithMinimumWeight(unsettledNodes);

            marked.add(minVertex);
            unsettledNodes.remove(minVertex);

            graph.adjacencyList(minVertex).stream().filter(commonVertex -> getShortestDistance(commonVertex) >
                    getShortestDistance(minVertex) + getDistance(minVertex, commonVertex)).forEachOrdered(commonVertex -> {
                distances.put(commonVertex, getShortestDistance(minVertex)
                        + getDistance(minVertex, commonVertex));
                unsettledNodes.add(commonVertex);
                edgeTo.put(commonVertex, minVertex);
            });
        }
    }

    private double getDistance(Vertex<T> A, Vertex<T> B) {
        for (Vertex<T> commonVertex : graph.getEdges(A).keySet()) {
            if (commonVertex.equals(B)) {
                return graph.getEdges(A).get(commonVertex);
            }
        }

        throw new RuntimeException("Not found!");
    }

    private Vertex<T> getVertexWithMinimumWeight(Set<Vertex<T>> vertices) {
        Vertex<T> minimum = null;

        for (Vertex<T> vertex : vertices) {
            if (minimum != null) {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex;
                }
            } else {
                minimum = vertex;
            }
        }

        return minimum;
    }

    private double getShortestDistance(Vertex<T> dest) {
        Double value = distances.get(dest);
        return (value == null ? Double.MAX_VALUE : value);
    }
}
