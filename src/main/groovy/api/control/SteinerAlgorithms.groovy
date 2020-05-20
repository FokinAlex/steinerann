package api.control

import ann.Model
import ann.ModelFacade
import ann.models.MstShiftModel
import api.ProjectFiles
import math.algorithms.Algorithm
import math.algorithms.steiner.ann.MstShift
import math.algorithms.steiner.ann.SelfOrganizingMap
import math.algorithms.steiner.ann.SelfOrganizingMap2
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
            new GreedyAlgorithm<Graph>(graph, GreedyAlgorithm.Sorter.BY_AVERAGE)
        }
    },

    GREEDY_ALGORITHM_2("Жадный алгоритм (2?)") {

        @Override
        Algorithm initialize(Graph graph) {
            new GreedyAlgorithm<Graph>(graph, GreedyAlgorithm.Sorter.BY_BREADTH_FIRST)
        }
    },

    SELF_ORGANIZING_MAP("Карта (временно)") {

        @Override
        Algorithm initialize(Graph graph) {
            new SelfOrganizingMap<Graph>(graph)
        }
    },

    SELF_ORGANIZING_MAP2("Карта сеть(временно)") {

        @Override
        Algorithm initialize(Graph graph) {
            new SelfOrganizingMap2<Graph>(graph)
        }
    },

    MST_SHIFT("MST Shift (TEMP 30)") {

        @Override
        Algorithm initialize(Graph graph) {
            new MstShift<Graph>(
                    graph,
                    ModelFacade.MST_SHIFT.load(
                            new File(ProjectFiles.ANN_MODELS_DIRECTORY,
                            "shift-model-30")
                    ) as MstShiftModel
            )
        }
    };

    final String name

    SteinerAlgorithms(String name) {
        this.name = name
    }

    @Override
    abstract Algorithm initialize(Graph graph)
}