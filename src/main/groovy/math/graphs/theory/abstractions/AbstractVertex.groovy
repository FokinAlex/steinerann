package math.graphs.theory.abstractions

import identification.Identifiable
import identification.Sequence
import math.graphs.VertexType
import math.graphs.theory.GraphTheoryEntity
import math.metricspaces.Point as MetricSpacePoint

abstract class AbstractVertex<Point extends MetricSpacePoint> extends Identifiable implements GraphTheoryEntity {

    final Point location
    VertexType type = VertexType.SIMPLE

    AbstractVertex(Sequence sequence, Point location) {
        super(sequence)
        this.location = location
    }
}
