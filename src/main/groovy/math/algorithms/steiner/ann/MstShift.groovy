package math.algorithms.steiner.ann

import ann.models.MstShiftModel
import math.algorithms.AbstractGraphAlgorithm
import math.graphs.theory.Graph

class MstShift<G extends Graph> extends AbstractGraphAlgorithm<G> {

    private final MstShiftModel model

    MstShift(G graph, MstShiftModel model) {
        super(graph, "Random Shift")
        this.model = model
    }

    @Override
    void run() {
        logStep "Started"

        model.calculate(graph)

        completed()
        logStep "Finished"
    }
}
