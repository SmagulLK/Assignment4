package com.graphs;

import java.util.*;

public class Search<T> {
    protected int count;
    protected Set<Vertex<T>> marked;
    protected Map<Vertex<T>, Vertex<T>> edgeTo;
    protected final Vertex<T> source;

    public Search(Vertex<T> source) {
        this.source = source;
        this.edgeTo = new HashMap<>();
        this.marked = new HashSet<>();
    }

    public Iterable<Vertex<T>> pathTo(Vertex<T> target) {
        if (!hasPathTo(target)) {
            return null;
        }

        LinkedList<Vertex<T>> list = new LinkedList<>();

        Vertex<T> temp = target;
        while (temp != source) {
            list.push(temp);
            temp = edgeTo.get(temp);
        }

        list.push(source);

        return list;
    }

    public boolean hasPathTo(Vertex<T> vertex) {
        return marked.contains(vertex);
    }

    public int getCount() {
        return count;
    }
}
