package gui.fxml.components.tabs

import api.enities.pages.OrlCasePage

class OrlCaseTab<P extends OrlCasePage> extends GraphTab<P> {

    OrlCaseTab(String name, P page) {
        super(name, page)
    }
}
