package gui.enities.pages

import javafx.scene.Node
import javafx.scene.layout.Pane

class GraphPage extends Page {

    GraphPage(String name) {
        super(name)
    }

    @Override
    Node getContent() {
        // Create new component in fxml.components
        new Pane()
    }
}
