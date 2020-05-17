package gui.control

import api.control.AlgorithmMonitor
import api.enities.pages.GraphPage
import api.enities.pages.OrlCasePage
import gui.Context
import gui.fxml.components.tabs.GraphTab
import gui.fxml.components.tabs.OrlCaseTab

class AlgorithmController {

    static final Map<String, List<String>> getAlgorithms() {
        AlgorithmMonitor.INSTANCE.ALGORITHMS
    }

    // TODO: return data to Panel
    static def runAlgorithm(String group, String name) {
        if (Context.PROJECT_CONTROLLER.currentTab instanceof GraphTab) {
            AlgorithmMonitor.INSTANCE.runAlgorithm(
                    group,
                    name,
                    (Context.PROJECT_CONTROLLER.currentTab as GraphTab).page as GraphPage
            )
            // TODO: add listener
        } else if (Context.PROJECT_CONTROLLER.currentTab instanceof OrlCaseTab) {
            AlgorithmMonitor.INSTANCE.runAlgorithm(
                    group,
                    name,
                    (Context.PROJECT_CONTROLLER.currentTab as OrlCaseTab).page as OrlCasePage
            )
            // TODO: add listener
        }
    }
}
