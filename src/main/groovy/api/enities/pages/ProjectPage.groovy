package api.enities.pages

import javafx.scene.Node
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane

class ProjectPage extends Page {

    // Move to component in fxml.components
    BorderPane content
    private Label projectNameLabel

    ProjectPage(String name) {
        super(name)
        this.content = new BorderPane()
        this.projectNameLabel = new Label(name)
        this.projectNameLabel.textProperty().bind(this.getName())
        this.content.setCenter(projectNameLabel)
    }

    @Override
    Node getContent() {
        this.content
    }
}
