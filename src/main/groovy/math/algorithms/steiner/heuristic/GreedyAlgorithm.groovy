package math.algorithms.steiner.heuristic

import math.algorithms.AbstractGraphAlgorithm
import math.graphs.VertexTypes
import math.graphs.theory.Graph
import math.graphs.theory.Vertex
import math.metricspaces.EuclideanPoint
import math.metricspaces.Point
import math.utils.GraphUtils
import math.utils.MetricSpaceUtils
import utils.others.Triple

class GreedyAlgorithm<G extends Graph> extends AbstractGraphAlgorithm<G> {

    // Triangles or Squares
    // Different sorts
    private final Sorter sorter

    GreedyAlgorithm(G graph, Sorter sorter) {
        super(graph, "Greedy Algorithm")
        this.sorter = sorter
    }

    @Override
    void run() {
        logStep "Started"
        graph.clearToRegular()

        logStep "Sorting started"
        List<Vertex> sortedVertices = sorter.sort(graph)
        logStep "Sorted"

        if (sortedVertices.size() < 3) {
            GraphUtils.minimalSpanningTree(graph)
        } else {
            Graph currentTree
            Graph subCurrentTree
            Graph bestTree
            Vertex a
            Vertex b
            Vertex c

            currentTree = new Graph(graph.metricSpace)
            a = currentTree.newVertex(sortedVertices[0].location)
            b = currentTree.newVertex(sortedVertices[1].location)
            c = currentTree.newVertex(sortedVertices[2].location)

            MetricSpaceUtils.of(currentTree.metricSpace).triangleSteinerTree(currentTree, a, b, c)

            for (int i = 3; i < sortedVertices.size(); i++) {
                bestTree = new Graph(graph.metricSpace) { @Override double getWeight() { Double.MAX_VALUE } }
                currentTree.edges.each { edge ->
                    subCurrentTree = GraphUtils.cloneGraph(currentTree)
                    subCurrentTree.removeEdge(edge.a, edge.b)
                    a = subCurrentTree.topology.vertices.find { it == edge.a }
                    b = subCurrentTree.topology.vertices.find { it == edge.b }
                    c = subCurrentTree.newVertex(sortedVertices[i].location)
                    MetricSpaceUtils.of(subCurrentTree.metricSpace).triangleSteinerTree(subCurrentTree, a, b, c)
                    if (subCurrentTree.weight < bestTree.weight) {
                        bestTree = GraphUtils.cloneGraph(subCurrentTree)
                    }
                }
                currentTree = GraphUtils.cloneGraph(bestTree)
            }
            currentTree.steinerPoints.each { graph.newSteinerPoint(it.location) }
            GraphUtils.minimalSpanningTree(graph)
            boolean hasExcess = true
            while (hasExcess) {
                List<Vertex> excessVertices = new LinkedList<>()
                graph.topology.vertices.each {
                    if (it.type == VertexTypes.STEINER && it.neighbors.size() != 3) {
                        excessVertices.add(it)
                    }
                }
                hasExcess = !excessVertices.isEmpty()
                graph.removeVertices(excessVertices.toArray() as Vertex[])
                GraphUtils.minimalSpanningTree(graph)
            }
        }

        completed()
        logStep "Finished"
    }

    enum Sorter {
        BY_AVERAGE {

            @Override
            List<Vertex> sort(Graph graph) {
                double averageX = 0
                double averageY = 0
                graph.topology.vertices.each {
                    averageX += (it.location as EuclideanPoint).x
                    averageY += (it.location as EuclideanPoint).y
                }
                averageX /= graph.topology.vertices.size()
                averageY /= graph.topology.vertices.size()
                Point averagePoint = graph.metricSpace.point(averageX, averageY)

                graph.topology.vertices.collect().sort { graph.metricSpace.metric(it.location, averagePoint) }
            }
        },

        BY_BREADTH_FIRST {

            @Override
            List<Vertex> sort(Graph graph) {
                List<Vertex> result = new LinkedList<>()
                List<Triple<Vertex, Vertex, Double>> completeGraphStructure = GraphUtils.completeGraphStructure(graph)
                        .sort{ it.c }

                completeGraphStructure.each {
                    if (!(it.a in result)) {
                        result.add(it.a)
                    }
                    if (!(it.b in result)) {
                        result.add(it.b)
                    }
                }
                result
            }
        };

        abstract List<Vertex> sort(Graph graph)
    }
}
