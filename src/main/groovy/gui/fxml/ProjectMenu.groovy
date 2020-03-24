package gui.fxml

import gui.Context
import javafx.fxml.FXML
import javafx.scene.control.Menu
import javafx.scene.control.MenuItem

trait ProjectMenu {

    @FXML private Menu     projectMenu
    @FXML private MenuItem projectNewPageMenuItem

    @FXML
    def newPage() {
        Context.PROJECT_CONTROLLER.newGraphPage("New Page")
    }
}