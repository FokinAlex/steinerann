package math.graphs.euclidean

import identification.Sequence
import math.graphs.theory.abstractions.AbstractEdge
import math.metricspaces.MetricSpace

class EuclideanEdge<Vertex extends EuclideanVertex> extends AbstractEdge<Vertex> {

    EuclideanEdge(Sequence sequence, Vertex first, Vertex second) {
        super(sequence, first, second)
    }

    @Override
    double getWeight() {
        MetricSpace.EUCLIDEAN.metric(this.first.getLocation(), this.second.getLocation())
    }
}
