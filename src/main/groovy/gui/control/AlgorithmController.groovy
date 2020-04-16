package gui.control

import api.control.AlgorithmType
import api.control.OtherGraphAlgorithms
import api.enities.pages.GraphPage
import gui.Context
import gui.fxml.components.tabs.GraphTab

class AlgorithmController {

    static private final  Map<String, Set<AlgorithmType>> INNER_ALGORITHMS
    static final  Map<String, List<String>> ALGORITHMS

    static {
        INNER_ALGORITHMS = new HashMap<>()
        ALGORITHMS = new HashMap<>()
        INNER_ALGORITHMS.put("Other", new HashSet<>(OtherGraphAlgorithms.values().collect()))
        ALGORITHMS.put("Other", OtherGraphAlgorithms.values().collect { it.name })
    }

    static def runAlgorithm(String group, String name) {
        if (Context.PROJECT_CONTROLLER.currentTab instanceof GraphTab) {
            INNER_ALGORITHMS.get(group).find { it.name == name }.run(
                    ((Context.PROJECT_CONTROLLER.currentTab as GraphTab).page as GraphPage).graph
            )
        }
    }
}
