package math.graphs.theory

import identification.Sequence
import math.metricspaces.MetricSpace
import math.metricspaces.Point
import utils.others.Duo

class Graph {

    final MetricSpace metricSpace
    final Topology topology
    final Set<Duo<Vertex, Vertex>> edges

    Graph(MetricSpace metricSpace) {
        this.metricSpace = metricSpace
        this.topology = new Topology()
        this.edges = new HashSet<>()
    }

    def newVertex(Point location) {
        topology.newVertex(location)
    }

    def removerVertex(Vertex vertex) {
        topology.removeVertex(vertex)
    }

    def newEdge(Vertex vertexA, Vertex vertexB) {
        if (topology.newEdge(vertexA, vertexB)) {
            edges.add(new Duo<Vertex, Vertex>(a: vertexA, b: vertexB))
        }
    }

     def removeEdge(Vertex vertexA, Vertex vertexB) {
         if (topology.removeEdge(vertexA, vertexB)) {
             edges.removeAll { (it.a == vertexA && it.b == vertexB) || (it.a == vertexB && it.b == vertexA) }
         }
    }

    double getWeight() {
        // TODO:
        // edges.isEmpty() ? 0 : edges.stream()
        //         .map(AbstractEdge.&getWeight())
        //         .reduce({a, b -> a + b})
        //         .get() as double
    }

    final class Topology {

        final Set<Vertex> vertices = new HashSet<>()

        private final Sequence sequence = new Sequence()

        private Vertex newVertex(Point location) {
            Vertex vertex = new Vertex(sequence, location)
            this.vertices.add(vertex)
            vertex
        }

        private def newEdge(Vertex vertexA, Vertex vertexB) {
            vertexA.neighbors.add(vertexB)
            vertexB.neighbors.add(vertexA)
        }

        private def removeVertex(Vertex vertex) {
            Iterator<Vertex> neighborsIterator = vertex.neighbors.iterator()
            while (neighborsIterator.hasNext()) {
                Vertex neighbor = neighborsIterator.next()
                neighbor.neighbors.remove(vertex)
                neighborsIterator.remove()
            }
        }

        private def removeEdge(Vertex vertexA, Vertex vertexB) {
            vertexA.neighbors.remove(vertexB)
            vertexB.neighbors.remove(vertexA)
        }
    }
}
