package gui.enities.pages

import javafx.scene.Node
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane

class ProjectPage extends Page {

    // Create new component in fxml.components
    BorderPane content

    ProjectPage(String name) {
        super(name)
        content = new BorderPane()
        content.setCenter(new Label(name))
    }

    @Override
    Node getContent() {
        content
    }
}
