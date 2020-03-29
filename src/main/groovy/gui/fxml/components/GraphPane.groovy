package gui.fxml.components


import javafx.scene.control.ScrollPane
import javafx.scene.layout.AnchorPane

class GraphPane extends ScrollPane {

    static final int WORK_GROUND_SIDE_SIZE = 600
    static final int WORK_GROUND_BORDER_SIZE = 10
    static final int MAIN_PANE_WIDTH = WORK_GROUND_SIDE_SIZE + WORK_GROUND_BORDER_SIZE * 2
    static final int MAIN_PANE_HEIGHT = WORK_GROUND_SIDE_SIZE + WORK_GROUND_BORDER_SIZE * 2

    AnchorPane mainPane
    AnchorPane graphPane

    GraphPane() {
        graphPane = new AnchorPane()
        graphPane.setMaxSize(WORK_GROUND_SIDE_SIZE, WORK_GROUND_SIDE_SIZE)
        graphPane.setMinSize(WORK_GROUND_SIDE_SIZE, WORK_GROUND_SIDE_SIZE)
        graphPane.setPrefSize(WORK_GROUND_SIDE_SIZE, WORK_GROUND_SIDE_SIZE)
        graphPane.setStyle("-fx-border-color: #b0b0b0;")

        mainPane = new AnchorPane(graphPane)
        mainPane.setMaxSize(MAIN_PANE_WIDTH, MAIN_PANE_HEIGHT)
        mainPane.setMinSize(MAIN_PANE_WIDTH, MAIN_PANE_HEIGHT)
        mainPane.setPrefSize(MAIN_PANE_WIDTH, MAIN_PANE_HEIGHT)

        mainPane.setTopAnchor(graphPane, 10)
        mainPane.setLeftAnchor(graphPane, 10)

        this.content = mainPane
    }
}
