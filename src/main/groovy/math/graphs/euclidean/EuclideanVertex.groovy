package math.graphs.euclidean

import identification.Sequence
import math.graphs.theory.abstractions.AbstractVertex
import math.metricspaces.Point as MetricSpacePoint

class EuclideanVertex<Point extends MetricSpacePoint> extends AbstractVertex<Point> {

    EuclideanVertex(Sequence sequence, Point location) {
        super(sequence, location)
    }
}
