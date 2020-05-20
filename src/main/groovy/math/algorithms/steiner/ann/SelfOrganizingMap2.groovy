package math.algorithms.steiner.ann

import math.algorithms.AbstractGraphAlgorithm
import math.graphs.VertexTypes
import math.graphs.theory.Graph
import math.graphs.theory.Vertex
import math.metricspaces.EuclideanPoint
import math.metricspaces.Point
import math.utils.GraphUtils
import math.utils.MetricSpaceUtils
import utils.others.Duo

class SelfOrganizingMap2<G extends Graph> extends AbstractGraphAlgorithm<G> {

    SelfOrganizingMap2(G graph) {
        super(graph, "Kohonen Self-Organizing Map (Net)")
    }

    @Override
    void run() {

        logStep "Started"
        graph.clearToRegular()

        double leftBorder   = Double.MAX_VALUE
        double rightBorder  = Double.MIN_VALUE
        double bottomBorder = Double.MAX_VALUE
        double topBorder    = Double.MIN_VALUE
        double xStep
        double yStep

        graph.topology.vertices.each {
            it = it.location as EuclideanPoint
            if (it.x < leftBorder)   leftBorder   = it.x
            if (it.x > rightBorder)  rightBorder  = it.x
            if (it.y < bottomBorder) bottomBorder = it.y
            if (it.y > topBorder)    topBorder    = it.y
        }
        xStep = (rightBorder - leftBorder) / graph.topology.vertices.size()
        yStep = (topBorder - bottomBorder) / graph.topology.vertices.size()

        List<Point> steinerPoints = new ArrayList<>(graph.topology.vertices.size() * graph.topology.vertices.size())
        for (int i = 0; i < graph.topology.vertices.size(); i++) {
            for (int j = 0; j < graph.topology.vertices.size(); j++) {
                steinerPoints.add(graph.metricSpace.point(
                        leftBorder   + xStep * i, // x
                        bottomBorder + yStep * j // y
                ))
            }
        }
        steinerPoints.each { graph.newSteinerPoint(it) }

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

        graph.topology.vertices.each {
            if (it.type == VertexTypes.STEINER) {
                // Promise that it.neighbors.size() == 3
                // logStep "Moving ${it}"
                it.location = MetricSpaceUtils.of(graph.metricSpace).steinerPointFor(
                        it.neighbors[0].location,
                        it.neighbors[1].location,
                        it.neighbors[2].location
                )
                // logStep "    to ${it}"
            }
        }


        completed()
        logStep "Finished"
    }
}
