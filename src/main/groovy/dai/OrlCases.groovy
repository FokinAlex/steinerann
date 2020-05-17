package dai

import api.enities.pages.OrlCasePage
import math.graphs.theory.Graph
import utils.orl.OrlCaseLoader
import utils.others.Duo

class OrlCases {

    static List<Duo<Integer, List<String>>> getCases() {
        OrlCaseLoader.getCases()
    }

    static OrlCasePage loadCase(String caseName) {
        Duo<Graph, Graph> duo = OrlCaseLoader.loadCase(caseName)
        new OrlCasePage(caseName, duo.a, duo.b)
    }
}
