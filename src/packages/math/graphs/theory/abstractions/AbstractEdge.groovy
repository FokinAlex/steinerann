package math.graphs.theory.abstractions

import math.graphs.theory.GraphTheoryEntity

abstract class AbstractEdge<V extends AbstractVertex> implements GraphTheoryEntity {
    // TODO: add annotation javax.annotation.@NotNull
    V first
    V second

    abstract double getWeight()

    boolean contains(V vertex) {
        first.equals(vertex) || second.equals(vertex)
    }
}
