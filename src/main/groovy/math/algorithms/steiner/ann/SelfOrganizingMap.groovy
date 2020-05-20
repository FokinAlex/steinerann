package math.algorithms.steiner.ann

import math.algorithms.AbstractGraphAlgorithm
import math.graphs.VertexTypes
import math.graphs.theory.Graph
import math.graphs.theory.Vertex
import math.metricspaces.EuclideanPoint
import math.utils.GraphUtils
import math.utils.MetricSpaceUtils
import utils.others.Duo

// Kohonen
class SelfOrganizingMap<G extends Graph> extends AbstractGraphAlgorithm<G> {

    SelfOrganizingMap(G graph) {
        super(graph, "Kohonen Self-Organizing Map (Each vertex)")
    }

    @Override
    void run() {
        logStep "Started"
        graph.clearToRegular()

        double leftBorder   = Double.MAX_VALUE
        double rightBorder  = Double.MIN_VALUE
        double bottomBorder = Double.MAX_VALUE
        double topBorder    = Double.MIN_VALUE
        double horizontalMargin
        double verticalMargin

        graph.topology.vertices.each {
            it = it.location as EuclideanPoint
            if (it.x < leftBorder)   leftBorder   = it.x
            if (it.x > rightBorder)  rightBorder  = it.x
            if (it.y < bottomBorder) bottomBorder = it.y
            if (it.y > topBorder)    topBorder    = it.y
        }
        horizontalMargin = (rightBorder - leftBorder) / graph.topology.vertices.size()
        verticalMargin   = (topBorder - bottomBorder) / graph.topology.vertices.size()

        List<Duo<Double, Double>> locations = new LinkedList<>()
        graph.topology.vertices.each {
            EuclideanPoint point = it.location as EuclideanPoint
            locations.add(new Duo(a: point.x                   , b: point.y + verticalMargin))
            locations.add(new Duo(a: point.x + horizontalMargin, b: point.y + verticalMargin))
            locations.add(new Duo(a: point.x + horizontalMargin, b: point.y                 ))
            locations.add(new Duo(a: point.x + horizontalMargin, b: point.y - verticalMargin))
            locations.add(new Duo(a: point.x                   , b: point.y - verticalMargin))
            locations.add(new Duo(a: point.x - horizontalMargin, b: point.y - verticalMargin))
            locations.add(new Duo(a: point.x - horizontalMargin, b: point.y                 ))
            locations.add(new Duo(a: point.x - horizontalMargin, b: point.y + verticalMargin))
        }
//        println "Let add some ${locations.size()}"
//        println "leftBorder       ${ leftBorder       }"
//        println "rightBorder      ${ rightBorder      }"
//        println "topBorder        ${ topBorder        }"
//        println "bottomBorder     ${ bottomBorder     }"
//        println "horizontalMargin ${ horizontalMargin }"
//        println "verticalMargin   ${ verticalMargin   }"
        locations.each {
            graph.newSteinerPoint(graph.metricSpace.point(it.a, it.b))
//            println "${graph.steinerPoints.size()} [${it.a}, ${it.b}]"
        }

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
