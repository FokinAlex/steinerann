package dai

import api.enities.pages.OrlCasePage
import utils.orl.OrlCaseLoader
import utils.others.Duo

class OrlCases {

    static List<Duo<String, List<String>>> getCases() {
        OrlCaseLoader.getCases()
    }

    static OrlCasePage loadCase(String caseName) {
        OrlCasePage page = new OrlCasePage(caseName, OrlCaseLoader.loadCase(caseName))
    }
}