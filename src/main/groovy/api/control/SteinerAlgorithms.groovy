package api.control

import math.algorithms.Algorithm
import math.algorithms.steiner.heuristic.GreedyAlgorithm
import math.algorithms.steiner.heuristic.SmithLeeLiebmanAlgorithm
import math.graphs.theory.Graph

enum SteinerAlgorithms implements AlgorithmType<Graph> {
    SMITH_LEE_LIEBMAN_ALGORITHM("Алгоритм Смита-Ли-Лейбмана") {

        @Override
        Algorithm initialize(Graph graph) {
            new SmithLeeLiebmanAlgorithm<Graph>(graph)
        }
    },

    GREEDY_ALGORITHM("Жадный алгоритм") {

        @Override
        Algorithm initialize(Graph graph) {
            new GreedyAlgorithm<Graph>(graph)
        }
    };

    final String name

    SteinerAlgorithms(String name) {
        this.name = name
    }

    @Override
    abstract Algorithm initialize(Graph graph)
}