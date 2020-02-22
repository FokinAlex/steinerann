package math.graphs.theory.abstractions

import identification.Identifiable
import identification.Sequence
import math.graphs.theory.GraphTheoryEntity

abstract class AbstractEdge<V extends AbstractVertex> extends Identifiable implements GraphTheoryEntity {

    protected final V first
    protected final V second

    AbstractEdge(Sequence sequence, V first, V second) {
        super(sequence)
        this.first = first
        this.second = second
    }

    // TODO: Weight is the Metric of first and second
    abstract double getWeight()

    // TODO: We can implement in-groovy-method
    boolean contains(V vertex) {
        first.equals(vertex) || second.equals(vertex)
    }
}
