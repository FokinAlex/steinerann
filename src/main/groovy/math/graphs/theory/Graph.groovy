package math.graphs.theory

import identification.Sequence
import math.graphs.VertexTypes
import math.metricspaces.MetricSpace
import math.metricspaces.Point
import utils.others.Duo

class Graph {

    final MetricSpace metricSpace
    final Topology topology
    final Set<Vertex> steinerPoints
    final Set<Duo<Vertex, Vertex>> edges

    Graph(MetricSpace metricSpace) {
        this.metricSpace = metricSpace
        this.topology = new Topology()
        this.edges = new HashSet<>()
        this.steinerPoints = new HashSet<>(
        )
    }

    Vertex newVertex(Point location) {
        topology.newVertex(location)
    }

    Vertex newSteinerPoint(Point location) {
        Vertex steinerPoint = newVertex(location)
        steinerPoint.type = VertexTypes.STEINER
        steinerPoints.add(steinerPoint)
        steinerPoint
    }

    def removeVertex(Vertex vertex) {
        if (topology.removeVertex(vertex)) {
            steinerPoints.remove(vertex)
            edges.removeAll { it.a == vertex || it.b == vertex }
        }
    }

    def removeVertices(Vertex... vertices) {
        vertices.each { removeVertex(it) }
    }

    def newEdge(Vertex vertexA, Vertex vertexB) {
        if (topology.newEdge(vertexA, vertexB)) {
            edges.add(new Duo<Vertex, Vertex>(a: vertexA, b: vertexB))
        } else {
            println "Почему-то ребро не добавлено a: ${vertexA} b: ${vertexB}"
        }
    }

     def removeEdge(Vertex vertexA, Vertex vertexB) {
         if (topology.removeEdge(vertexA, vertexB)) {
             edges.removeAll { (it.a == vertexA && it.b == vertexB) || (it.a == vertexB && it.b == vertexA) }
         } else {
             println "Почему-то ребро не удалено a: ${vertexA} b: ${vertexB}"
         }
    }

    def clearToRegular() {
        topology.vertices.each { it.neighbors.clear() }
        edges.clear()
        steinerPoints.each { topology.removeVertex(it) }
        steinerPoints.clear()
    }

    def clearEdges() {
        topology.vertices.each { it.neighbors.clear() }
        edges.clear()
    }

    double getWeight() {
        edges.isEmpty() ?
                0 :
                edges.stream()
                        .map { metricSpace.metric(it.a.location, it.b.location) }
                        .reduce { a, b -> a + b }
                        .get() as Double
    }

    final class Topology {

        final Set<Vertex> vertices = new HashSet<>()

        private final Sequence sequence = new Sequence()

        private Vertex newVertex(Point location) {
            Vertex vertex = new Vertex(sequence, location)
            this.vertices.add(vertex)
            vertex
        }

        def newEdge(Vertex vertexA, Vertex vertexB) {
            vertexA.neighbors.add(vertexB) && vertexB.neighbors.add(vertexA)
        }

        def removeVertex(Vertex vertex) {
            Iterator<Vertex> neighborsIterator = vertex.neighbors.iterator()
            while (neighborsIterator.hasNext()) {
                Vertex neighbor = neighborsIterator.next()
                neighbor.neighbors.remove(vertex)
                neighborsIterator.remove()
            }
            vertices.remove(vertex)
        }

        def removeEdge(Vertex vertexA, Vertex vertexB) {
            vertexA.neighbors.remove(vertexB) && vertexB.neighbors.remove(vertexA)
        }
    }
}
