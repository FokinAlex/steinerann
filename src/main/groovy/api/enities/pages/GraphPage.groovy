package api.enities.pages

import gui.fxml.components.GraphPane
import javafx.scene.Node

class GraphPage extends Page {

    // TODO: Graph here
    GraphPane content

    GraphPage(String name) {
        super(name)
        content = new GraphPane()
    }

    @Override
    Node getContent() {
        content
    }
}
