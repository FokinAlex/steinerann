package math.utils

import math.graphs.implementations.EuclideanVertex
import math.graphs.theory.abstractions.AbstractVertex

enum MetricSpaces {
    EUCLIDEAN {
        Double squareMetric(AbstractVertex first, AbstractVertex second) {
            if (first instanceof EuclideanVertex && second instanceof EuclideanVertex) {
                MathUtils.square(first.x - second.x) + MathUtils.square(first.y - second.y)
            } else {
                throw new IllegalArgumentException("Not euclidean vertex in Euclidean space")
            }
        }
    };

    /**
     * @param first     First point in metric space
     * @param second    Second point in metric space
     * @return          Square of metric in Double
     */
    abstract Double squareMetric(AbstractVertex first, AbstractVertex second)
}
