package gui.fxml.components

import javafx.scene.Node
import javafx.scene.control.ScrollPane
import javafx.scene.layout.AnchorPane

class PageContentPane extends ScrollPane {

    final AnchorPane mainPane

    // TODO: Parameters.MAIN_PANE_WIDTH, Parameters.MAIN_PANE_HEIGHT
    PageContentPane(int width, int height) {
        mainPane = new AnchorPane()
        mainPane.setMaxSize(width, height)
        mainPane.setMinSize(width, height)
        mainPane.setPrefSize(width, height)
        this.content = mainPane
    }

    def addNode(Node node, int x, int y) {
        mainPane.getChildren().add(node)
        mainPane.setLeftAnchor(node, x)
        mainPane.setTopAnchor(node, y)
    }
}
