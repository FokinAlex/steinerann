package math.algorithms.steiner

import math.algorithms.AbstractGraphAlgorithm
import math.algorithms.other.KruskallAlgorithm
import math.graphs.VertexTypes
import math.graphs.theory.Graph
import math.graphs.theory.Vertex
import math.metricspaces.Point
import math.utils.GraphUtils
import math.utils.MetricSpaceUtils
import utils.others.Triple

class SmithLeeLiebmanAlgorithm<G extends Graph> extends AbstractGraphAlgorithm<G> {

    SmithLeeLiebmanAlgorithm(G graph) {
        super(graph, "Smith-Lee-Liebman Algorithm")
    }

    @Override
    void run() {
        logStep "Started"

        // 1. Build triamgulation ?
        logStep "Triangulation started"
        List<Triple<Vertex, Vertex, Vertex>> triangles = GraphUtils.triangulation(graph)
        logStep "Triangulation finished"

        if (null == triangles || triangles.size() < 1) {
            logStep "Have no triangles"
            new KruskallAlgorithm(graph).run()
        } else {
            // 2. For each triangle find steiner point
            triangles.each {
                Point steinerPoint = MetricSpaceUtils.of(graph.metricSpace).steinerPointFor(it.a.location, it.b.location, it.c.location)
                logStep "${steinerPoint} is steiner point for ${it}"
                if (steinerPoint != it.a.location && steinerPoint != it.b.location && steinerPoint != it.c.location) {
                    graph.newVertex(steinerPoint).type = VertexTypes.STEINER
                    logStep "New vertex added"
                }
            }

            // 3.
            logStep "Building minimal spanning tree"
            new KruskallAlgorithm(graph).run()
            logStep "Building Steiner minimal tree"
            boolean hasExcess = true
            while (hasExcess) {
                List<Vertex> excessVertices = new LinkedList<>()

                logStep "Finding all excess vertices"
                graph.topology.vertices.each {
                    if (it.type == VertexTypes.STEINER && (it.neighbors.size() < 3 || it.neighbors.size() > 4)) {
                        logStep "${it} finded"
                        excessVertices.add(it)
                    }
                }

                hasExcess = !excessVertices.isEmpty()

                logStep "Removing all excess vertices (${excessVertices.size()})"
                graph.removeVertices(excessVertices.toArray() as Vertex[])

                logStep "Building minimal spanning tree"
                new KruskallAlgorithm(graph).run()
            }

            logStep "Optimazing locations of Steiner points"
            graph.topology.vertices.each {
                if (it.type == VertexTypes.STEINER) {
                    // Promise that it.neighbors.size() == 3
                    logStep "Moving ${it}"
                    it.location = MetricSpaceUtils.of(graph.metricSpace).steinerPointFor(
                            it.neighbors[0].location,
                            it.neighbors[1].location,
                            it.neighbors[2].location
                    )
                    logStep "    to ${it}"
                }
            }
        }

        completed()
        logStep "Finished"
    }
}
