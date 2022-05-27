package com.graphs;

import java.util.*;

public class WeightedGraph<T> {
    private boolean undirected;
    private List<Vertex<T>> vertices = new ArrayList<>();

    WeightedGraph(boolean undirected) {
        this.undirected = undirected;
    }

    public void addVertex(Vertex<T> vertex) {
        vertices.add(vertex);
    }

    public void addEdge(Vertex<T> A, Vertex<T> B, double weight) {
        if (!hasVertex(A)) {
            addVertex(A);
        }

        if (!hasVertex(B)) {
            addVertex(B);
        }

        if (hasEdge(A, B) || A.equals(B)) {
            return;
        }

        if (!undirected) {
            for (Vertex<T> vertex : vertices) {
                if (vertex.equals(A) && !hasEdge(A, B)) {
                    vertex.addAdjacentVertex(B, weight);
                }
            }
        } else {
            for (Vertex<T> vertex : vertices) {
                if (vertex.equals(A) && !hasEdge(A, B)) {
                    vertex.addAdjacentVertex(B, weight);
                }

                if (vertex.equals(B) && !hasEdge(B, A)) {
                    vertex.addAdjacentVertex(A, weight);
                }
            }
        }

    }

    public boolean hasVertex(Vertex<T> vertex) {
        for (Vertex<T> element : vertices) {
            if (element.getData().equals(vertex.getData())) {
                return true;
            }
        }

        return false;
    }

    public boolean hasEdge(Vertex<T> destinationFrom, Vertex<T> destinationTo) {
        if (!hasVertex(destinationFrom)) {
            return false;
        }

        for (Vertex<T> element : vertices) {
            if (element.getData().equals(destinationFrom) && element.getAdjacentVertices() != null) {
                for (Vertex<T> elementVertex : element.getAdjacentVertices().keySet()) {
                    if (elementVertex.getData().equals(destinationTo)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public Map<Vertex<T>, Double> getEdges(Vertex<T> vertex) {
        if (!hasVertex(vertex)) {
            return null;
        }

        for (Vertex<T> element : vertices) {
            if (element.getData().equals(vertex.getData())) {
                return element.getAdjacentVertices();
            }
        }

        return null;
    }

    public List<Vertex<T>> adjacencyList(Vertex<T> vertex) {
        if (!hasVertex(vertex)) {
            return null;
        }

        List<Vertex<T>> vertices = new LinkedList<>();

        for (Vertex<T> element : this.vertices) {
            if (element.getData().equals(vertex.getData())) {
                vertices.addAll(element.getAdjacentVertices().keySet());
                break;
            }
        }

        return vertices;
    }

}
