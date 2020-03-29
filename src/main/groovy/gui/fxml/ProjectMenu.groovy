package gui.fxml

import gui.Context
import javafx.fxml.FXML
import javafx.scene.control.Menu
import javafx.scene.control.MenuItem

trait ProjectMenu {

    @FXML private Menu     projectMenu
    @FXML private MenuItem projectNewGraphPageMenuItem
    @FXML private MenuItem projectRenameProjectMenuItem

    @FXML
    def newGraphPage() {
        Context.PROJECT_CONTROLLER.newGraphPage("New Page")
    }

    @FXML
    def renameProject() {
        Context.PROJECT_CONTROLLER.renameProject("Sas dvas")
    }
}