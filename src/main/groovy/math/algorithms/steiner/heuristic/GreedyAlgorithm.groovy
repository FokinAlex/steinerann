package math.algorithms.steiner.heuristic

import math.algorithms.AbstractGraphAlgorithm
import math.algorithms.other.KruskallAlgorithm
import math.graphs.theory.Graph
import math.graphs.theory.Vertex
import math.metricspaces.EuclideanPoint
import math.metricspaces.Point
import math.utils.GraphUtils
import math.utils.MetricSpaceUtils
import utils.others.Duo

class GreedyAlgorithm<G extends Graph> extends AbstractGraphAlgorithm<G> {

    // Triangles or Squares
    // Different sorts
    private final Sorter sorter

    GreedyAlgorithm(G graph) {
        super(graph, "Greedy Algorithm")
        sorter = Sorter.BY_AVERAGE
    }

    @Override
    void run() {
        logStep "Started"

        logStep "Sorting started"
        List<Vertex> sortedVertices = sorter.sort(graph)

        if (sortedVertices.size() < 3) {
            new KruskallAlgorithm(graph).run()
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
                Iterator edgesIterator = currentTree.edges.iterator()
                while (edgesIterator.hasNext()) {
                    Duo<Vertex, Vertex> edge = edgesIterator.next()
                    subCurrentTree = GraphUtils.cloneGraph(currentTree)
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
            new KruskallAlgorithm(graph).run()
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
        };

        abstract List<Vertex> sort(Graph graph)
    }
}
