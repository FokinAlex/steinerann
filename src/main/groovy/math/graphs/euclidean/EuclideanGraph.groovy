package math.graphs.euclidean

import math.graphs.theory.abstractions.AbstractGraph
import math.metricspaces.MetricSpace

class EuclideanGraph<Vertex extends EuclideanVertex, Edge extends EuclideanEdge<Vertex>> extends AbstractGraph<Vertex, Edge> {

    EuclideanGraph() {
        super(MetricSpace.EUCLIDEAN)
    }
}
