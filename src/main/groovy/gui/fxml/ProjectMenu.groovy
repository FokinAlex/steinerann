package gui.fxml

import gui.Context
import javafx.fxml.FXML
import javafx.scene.control.Menu
import javafx.scene.control.MenuItem

trait ProjectMenu {

    @FXML private Menu     projectMenu
    @FXML private MenuItem projectGenerateGraphPageMenuItem
    @FXML private Menu     projectNewOrlCaseMenu

    @FXML
    def generateGraphPage() {

        // TODO: get name & number
        int number = 10
        String name = "${Math.random() * 10_000 % 10_000 as int}.${number}"


        Context.PROJECT_CONTROLLER.generateGraphPage(name, number)
    }
}