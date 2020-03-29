package gui.fxml

import gui.Context
import javafx.fxml.FXML
import javafx.scene.control.Menu
import javafx.scene.control.MenuItem

trait FileMenu {

    @FXML private Menu     fileMenu
    @FXML private MenuItem fileNewProjectMenuItem
    @FXML private MenuItem fileCloseProjectMenuItem

    @FXML
    def newProject() {
        Context.PROJECT_CONTROLLER.newProject("New Project")
    }

    @FXML
    def closeProject() {
        Context.PROJECT_CONTROLLER.closeProject()
    }
}