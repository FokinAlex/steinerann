package math.algorithms.graph

import math.graphs.theory.Graph
import math.graphs.theory.Vertex
import math.utils.GraphUtils
import utils.others.Triple

final class KruskallAlgorithm<G extends Graph> extends AbstractGraphAlgorithm<G> {

    KruskallAlgorithm(G graph) {
        super(graph)
    }

    @Override
    void run() {
        // this.inProgress.set(true);

        List<Triple<Vertex, Vertex, Double>> completeGraphStructure = GraphUtils.completeGraphStructure(graph)

        completeGraphStructure.sort { it.c }

        completeGraphStructure.each {
            if (!GraphUtils.isReachable(it.a, it.b)) {
                graph.newEdge(it.a, it.b)
                // TODO: step(...)            if (!GraphUtils.isReachable(it.a, it.b)) {

            }
        }

        // this.inProgress.set(false);
    }
}
