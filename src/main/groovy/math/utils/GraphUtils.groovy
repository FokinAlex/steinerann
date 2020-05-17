package math.utils

import log.LogFacade
import math.graphs.VertexTypes
import math.graphs.theory.Graph
import math.graphs.theory.Vertex
import math.metricspaces.EuclideanPoint
import math.utils.delaunaytriangulator.DelaunayTriangulator
import math.utils.delaunaytriangulator.Vector2D
import utils.others.Triple

final class GraphUtils {

    static final Graph cloneGraph(Graph graph) {
        Graph result = new Graph(graph.metricSpace)
        graph.topology.vertices.sort { it.hashCode() }.each {
            if (it.type == VertexTypes.STEINER) {
                result.newSteinerPoint(it.location)
            } else {
                result.newVertex(it.location)
            }
        }
        graph.edges.each { edge ->
            result.newEdge(
                    result.topology.vertices.find { it == edge.a },
                    result.topology.vertices.find { it == edge.b }
            )
        }
        result
    }

    static final List<Triple<Vertex, Vertex, Double>> completeGraphStructure(Graph graph) {
        List<Triple<Vertex, Vertex, Double>> structure = new LinkedList<>()
        // Set.iterator().remove() - UnsupportedOperationException
        HashSet<Vertex> tempSet = Set.copyOf(graph.topology.vertices as Collection<Vertex>)
        Iterator<Vertex> verticesIterator = tempSet.iterator()
        while (verticesIterator.hasNext()) {
            Vertex vertexA = verticesIterator.next() as Vertex
            verticesIterator.remove()
            tempSet.each { vertexB ->
                structure.add(new Triple(
                        a: vertexA,
                        b: vertexB,
                        c: graph.metricSpace.metric(vertexA.location, vertexB.location)
                ))
            }
        }
        structure
    }

    static final void makeComplete(Graph graph) {
        Set<Vertex> visited = new HashSet<>()
        graph.topology.vertices.each { vertex ->
            visited.add(vertex)
            graph.topology.vertices.each {
                if (it != vertex && !visited.contains(it)) {
                    graph.newEdge(vertex, it)
                }
            }
        }
    }

    static final boolean isReachable(Vertex start, Vertex goal) {
        final Map<Vertex, Boolean> visits = new HashMap<>()
        final Queue<Vertex> queue = new LinkedList<>()
        Vertex vertex

        // Breadth-First Search
        queue.push(start)
        visits.put(start, true)
        while (!queue.isEmpty()) {
            vertex = queue.pop()
            if (vertex == goal) return true
            vertex.neighbors.each {
                if (!visits.get(it)) {
                    queue.push(it)
                    visits.put(it, true)
                }
            }
        }
        return false
    }

    static final List<Triple<Vertex, Vertex, Vertex>> triangulation(Graph graph) {
        if (graph.topology.vertices.size() < 3) {
            LogFacade.WARN(new IllegalArgumentException("Less than three points in point set"))
            return null
        }

        Map<Vector2D, Vertex> mapping = graph.topology.vertices.collectEntries {
            [new Vector2D((it.location as EuclideanPoint).x.value, (it.location as EuclideanPoint).y.value), it]
        }

        DelaunayTriangulator triangulator = new DelaunayTriangulator(mapping.keySet().toList())
        triangulator.triangulate()
        triangulator.getTriangles().stream()
            .map { new Triple<Vertex, Vertex, Vertex>(
                    a: mapping.get(it.a), // TODO: as Vertex ?
                    b: mapping.get(it.b), // TODO: as Vertex ?
                    c: mapping.get(it.c)  // TODO: as Vertex ?
            )}
            .collect { it }
    }
}

/*
    static void main(String[] args) {
    Sequence sequence = new Sequence()
    Vertex vertex1 = new Vertex<EuclideanPoint>(sequence, MetricSpace.EUCLIDEAN.point(1d, 3d))
    Vertex vertex2 = new Vertex<EuclideanPoint>(sequence, MetricSpace.EUCLIDEAN.point(1d, 3d))
    Vertex vertex3 = new Vertex<EuclideanPoint>(sequence, MetricSpace.EUCLIDEAN.point(1d, 3d))
    Vertex vertex4 = new Vertex<EuclideanPoint>(sequence, MetricSpace.EUCLIDEAN.point(1d, 3d))
    Vertex vertex5 = new Vertex<EuclideanPoint>(sequence, MetricSpace.EUCLIDEAN.point(1d, 3d))
    Vertex vertex6 = new Vertex<EuclideanPoint>(sequence, MetricSpace.EUCLIDEAN.point(1d, 3d))
    vertex1.neighbors.add(vertex2)
    vertex2.neighbors.add(vertex1)
    vertex1.neighbors.add(vertex3)
    vertex3.neighbors.add(vertex1)
    vertex2.neighbors.add(vertex4)
    vertex4.neighbors.add(vertex2)
    vertex2.neighbors.add(vertex5)
    vertex5.neighbors.add(vertex2)
    vertex3.neighbors.add(vertex5)
    vertex5.neighbors.add(vertex3)
    Set<Vertex> set = Set.of(
            vertex1 as Vertex,
            vertex2 as Vertex,
            vertex3 as Vertex,
            vertex4 as Vertex,
            vertex5 as Vertex,
            vertex6 as Vertex
    )
    println isReachable(vertex1, vertex5)
    println isReachable(vertex3, vertex6)
}
// */
