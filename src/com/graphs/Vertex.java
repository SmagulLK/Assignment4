package com.graphs;

import java.util.HashMap;
import java.util.Map;

public class Vertex<V> {
    private final V data;
    private final Map<Vertex<V>, Double> adjacentVertices = new HashMap<>();

    public Vertex(V data) {
        this.data = data;
    }

    public void addAdjacentVertex(Vertex<V> B, double weight) {
        if(!data.equals(B.getData())) {
            adjacentVertices.put(B, weight);
        }
    }

    public Map<Vertex<V>, Double> getAdjacentVertices() {
        return adjacentVertices;
    }

    public V getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Vertex<?> vertex = (Vertex<?>) o;
        return data.equals(vertex.data);
    }
}
