package math.algorithms.other

import math.algorithms.AbstractGraphAlgorithm
import math.graphs.theory.Graph
import math.graphs.theory.Vertex
import math.utils.GraphUtils
import utils.others.Triple

final class KruskallAlgorithm<G extends Graph> extends AbstractGraphAlgorithm<G> {

    KruskallAlgorithm(G graph) {
        super(graph, "Kruskall Algorithm")
        // TODO: remove edges?
    }

    @Override
    void run() {
        logStep "Started"

        List<Triple<Vertex, Vertex, Double>> completeGraphStructure = GraphUtils.completeGraphStructure(graph)
        completeGraphStructure.sort { it.c }
        logStep "Preparing completed"

        completeGraphStructure.each {
            if (!GraphUtils.isReachable(it.a, it.b)) {
                graph.newEdge(it.a, it.b)
                logStep "New edge [${it.a.hashCode()} : ${it.b.hashCode()}]"
                // TODO: step(...)
            }
        }

        completed()
        logStep "Finished"
    }
}
