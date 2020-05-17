package api.control

import math.algorithms.Algorithm
import math.algorithms.other.KruskallAlgorithm
import math.graphs.theory.Graph

// TODO: Observer
enum OtherGraphAlgorithms implements AlgorithmType<Graph> {
    KRUSKALL_ALGORITHM("Алгоритм Краскала") {

        @Override
        Algorithm initialize(Graph graph) {
            new KruskallAlgorithm<Graph>(graph)
        }
    };

    final String name

    OtherGraphAlgorithms(String name) {
        this.name = name
    }

    @Override
    abstract Algorithm initialize(Graph graph)
}
