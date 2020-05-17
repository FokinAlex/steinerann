package math.algorithms.steiner.ann

import math.algorithms.AbstractGraphAlgorithm
import math.graphs.theory.Graph

class PerceptronAlgorithm<G extends Graph> extends AbstractGraphAlgorithm<G> {

    PerceptronAlgorithm(G graph) {
        // need model here
        super(graph, "Ann-Sosan")
    }

    @Override
    void run() {
        logStep "Started"

        // TODO: ...

        completed()
        logStep "Finished"
    }
}
