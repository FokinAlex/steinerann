package api.control

import math.algorithms.Algorithm
import math.algorithms.other.KruskallAlgorithm
import math.algorithms.steiner.SmithLeeLiebmanAlgorithm
import math.graphs.theory.Graph

// TODO: Observer
enum OtherGraphAlgorithms implements AlgorithmType<Graph> {
    // TODO: replace with param
    KRUSKALL_ALGORITHM("Kruskall Algorithm") {

        @Override
        Algorithm initialize(Graph graph) {
            new KruskallAlgorithm<Graph>(graph)
        }
    },
    // TODO: [!] temp, move to steiner algorithm
    SMITH_LEE_LIEBMAN_ALGORITHM("Smith-Lee-Liebman Algorithm") {

        @Override
        Algorithm initialize(Graph graph) {
            new SmithLeeLiebmanAlgorithm<Graph>(graph)
        }
    };

    final String name

    OtherGraphAlgorithms(String name) {
        this.name = name
    }

    @Override
    abstract Algorithm initialize(Graph graph)
}
