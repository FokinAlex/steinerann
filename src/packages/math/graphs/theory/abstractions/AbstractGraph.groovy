package math.graphs.theory.abstractions

import math.graphs.theory.GraphTheoryEntity

abstract class AbstractGraph<V extends AbstractVertex, E extends AbstractEdge<V>> implements GraphTheoryEntity {
    Set<V> vertexes
    Set<E> edges

    // TODO: add annotation javax.annotation.@NotNull
    boolean addVertex(V vertex) {
        vertexes.add(vertex)
    }

    // TODO: add annotation javax.annotation.@NotNull
    boolean removerVertex(V vertex) {
        edges.each {
            if (it.contains(vertex))
                removeEdge(it)
        }
        vertexes.remove(vertex)
    }

    // TODO: add annotation javax.annotation.@NotNull
    boolean addEdge(E edge) {
        // TODO: check if vertexes.contains(edge.first or edge.second)?
        edges.add(edge)
    }

    // TODO: add annotation javax.annotation.@NotNull
    boolean removeEdge(E edge) {
        edges.remove(edge)
    }

    double getWeight() {
        edges.isEmpty() ? 0 : edges.stream()
                .map(AbstractEdge.&getWeight())
                .reduce({a, b -> a + b}).get() ?: 0 as double
    }
}
