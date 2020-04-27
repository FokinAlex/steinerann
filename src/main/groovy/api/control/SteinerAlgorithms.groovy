package api.control

import math.algorithms.Algorithm
import math.algorithms.steiner.SmithLeeLiebmanAlgorithm
import math.graphs.theory.Graph

enum SteinerAlgorithms implements AlgorithmType<Graph> {
    SMITH_LEE_LIEBMAN_ALGORITHM("Алгоритм Смита-Ли-Лейбмана") {

        @Override
        Algorithm initialize(Graph graph) {
            new SmithLeeLiebmanAlgorithm<Graph>(graph)
        }
    };

    final String name

    SteinerAlgorithms(String name) {
        this.name = name
    }

    @Override
    abstract Algorithm initialize(Graph graph)
}