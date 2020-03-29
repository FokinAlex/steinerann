package gui.fxml.components.tabs

import api.enities.pages.GraphPage

class GraphTab<P extends GraphPage> extends AbstractTab<P> {

    GraphTab(String name, P page) {
        super(name, page)
    }
}
