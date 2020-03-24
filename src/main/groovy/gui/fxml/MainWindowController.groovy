package gui.fxml

import javafx.fxml.FXML
import javafx.scene.control.Menu
import javafx.scene.control.MenuBar
import javafx.scene.layout.BorderPane

final class MainWindowController implements FileMenu, ProjectMenu {

    // MenuBar:
    @FXML MenuBar menuBar

    @FXML Menu editMenu

    @FXML Menu pageMenu

    @FXML Menu algorithmsMenu

    // Panes:
    @FXML BorderPane mainPane

    // Initialization:
    @FXML
    void initialize() { }

    // Actions:
    @FXML
    boolean ping() {
        println "pong"
    }
}