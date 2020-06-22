package gui.fxml

import gui.Context
import javafx.fxml.FXML
import javafx.scene.control.Menu
import javafx.scene.control.MenuItem
import utils.DialogUtils
import utils.others.Duo

trait ProjectMenu {

    @FXML private Menu     projectMenu
    @FXML private MenuItem projectGenerateGraphPageMenuItem
    @FXML private Menu     projectNewOrlCaseMenu

    @FXML
    def generateGraphPage() {
        Duo<String, Integer> result = DialogUtils.showGenerateGraphDialog()
        if (result) {
            Context.PROJECT_CONTROLLER.generateGraphPage(result.a, result.b)
        }
    }
}