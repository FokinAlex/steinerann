package math.algorithms.graph

import math.algorithms.Algorithm
import math.graphs.theory.Graph

// TODO: Observable
abstract class AbstractGraphAlgorithm<G extends Graph> implements Algorithm<G> {

    final G graph

    AbstractGraphAlgorithm(G graph) {
        this.graph = graph
    }

    @Override
    def step() {
        // TODO: make a record
        return null
    }
}
