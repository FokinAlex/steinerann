package math.graphs.theory.abstractions

import math.graphs.theory.GraphTheoryEntity
import math.graphs.theory.MetricSpacePoint

abstract class AbstractVertex implements GraphTheoryEntity, MetricSpacePoint {
    abstract double getEuclideanX()
    abstract double getEuclideanY()
}
