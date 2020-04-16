package gui.control

import dai.OrlCases
import gui.Context
import utils.others.Duo

class OrlCaseController {

    static final List<Duo<Integer, List<String>>> CASES = OrlCases.getCases()

    static def loadCase(String caseName) {
        Context.PROJECT_CONTROLLER.newPage(OrlCases.loadCase(caseName))
    }
}
