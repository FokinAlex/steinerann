package math.graphs.implementations

import math.graphs.theory.abstractions.AbstractEdge
import math.utils.MetricSpaces

class EuclideanEdge<V extends EuclideanVertex> extends AbstractEdge<V> {
    @Override
    double getWeight() {
        Math.sqrt(MetricSpaces.EUCLIDEAN.squareMetric(first, second))
    }
}
