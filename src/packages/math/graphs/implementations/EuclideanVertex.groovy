package math.graphs.implementations

import math.graphs.theory.abstractions.AbstractVertex

class EuclideanVertex extends AbstractVertex {
    double x
    double y

    @Override
    double getEuclideanX() {
        return x
    }

    @Override
    double getEuclideanY() {
        return y
    }
}
