package math.utils

import math.graphs.theory.Graph
import math.graphs.theory.Vertex
import utils.others.Triple

final class GraphUtils {

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
    /*
     */

//    static boolean isTree(STBGraph graph) throws StackOverflowError {
//        Map<STBTerminal, Boolean> terminals = new HashMap<>();
//        graph.getAllVertexes().forEach(terminal -> terminals.put((STBTerminal) terminal, false));
//
//        Iterator<STBTerminal> iterator = terminals.keySet().iterator();
//        while (iterator.hasNext()) {
//            STBTerminal terminal = iterator.next();
//            if (hasCycle(terminal, terminals, null, graph)) return false;
//            terminals.entrySet().forEach(entry -> entry.setValue(false));
//        }
//        return true;
//    }
}
