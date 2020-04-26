package math.graphs.theory

import identification.Identifiable
import identification.Sequence
import math.graphs.VertexTypes
import math.metricspaces.Point

class Vertex<Location extends Point> extends Identifiable {

    Location location
    final Set<Vertex> neighbors = new HashSet<>()
    VertexTypes type = VertexTypes.SIMPLE

    Vertex(Sequence sequence, Location location) {
        super(sequence)
        this.location = location
    }

    @Override
    String toString() {
        "#${id} (${location})"
    }
}
