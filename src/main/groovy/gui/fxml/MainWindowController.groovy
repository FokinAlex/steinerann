package gui.fxml

import gui.Context
import javafx.fxml.FXML
import javafx.scene.control.Menu
import javafx.scene.control.MenuBar
import javafx.scene.control.MenuItem
import javafx.scene.layout.BorderPane

final class MainWindowController implements FileMenu, ProjectMenu, AlgorithmsMenu {

    // MenuBar:
    @FXML MenuBar menuBar

    @FXML Menu editMenu

    @FXML Menu pageMenu

    // Panes:
    @FXML BorderPane mainPane

    // Initialization:
    @FXML
    void initialize() {
        Context.ORL_CASE_CONTROLLER.CASES.each { group ->
            Menu menu = new Menu(group.a.toString())
            this.gui_fxml_ProjectMenu__projectNewOrlCaseMenu.getItems().add(menu)
            group.b.each { caseName ->
                MenuItem item = new MenuItem(caseName)
                item.setOnAction { Context.ORL_CASE_CONTROLLER.loadCase(caseName) }
                menu.getItems().add(item)
            }
        }
        Context.ALGORITHM_CONTROLLER.ALGORITHMS.each { group ->
            Menu menu = new Menu(group.key)
            this.gui_fxml_AlgorithmsMenu__algorithmsMenu.getItems().add(menu)
            group.value.each { algorithmName ->
                MenuItem item = new MenuItem(algorithmName)
                item.setOnAction { Context.ALGORITHM_CONTROLLER.runAlgorithm(group.key, algorithmName) }
                menu.getItems().add(item)
            }
        }
    }
}