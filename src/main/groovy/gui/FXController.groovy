package gui

import javafx.fxml.FXML
import javafx.scene.control.Menu
import javafx.scene.control.MenuBar
import javafx.scene.layout.BorderPane

class FXController {

    // MenuBar:
    @FXML private MenuBar menuBar

    @FXML private Menu fileMenu

    @FXML private Menu editMenu

    @FXML private Menu projectMenu

    @FXML private Menu pageMenu

    @FXML private Menu algorithmsMenu

    // Panes:
    @FXML private BorderPane mainPane

    // Initialization:
    @FXML
    void initialize() { }

    // Actions:
    @FXML
    boolean ping() {
        println "pong"
    }
}