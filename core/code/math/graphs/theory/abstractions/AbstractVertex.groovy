package math.graphs.theory.abstractions

import identification.Identifiable
import identification.Sequence
import math.graphs.theory.GraphTheoryEntity
import math.metricspaces.MetricSpace

abstract class AbstractVertex<Point extends MetricSpace.Point> extends Identifiable implements GraphTheoryEntity {

    protected Point location

    AbstractVertex(Sequence sequence, Point location) {
        super(sequence)
        this.location = location
    }
}
