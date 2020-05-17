package gui.fxml

import gui.Context
import javafx.fxml.FXML
import javafx.scene.control.Menu
import javafx.scene.control.MenuBar
import javafx.scene.control.MenuItem
import javafx.scene.control.ProgressBar
import javafx.scene.control.ProgressIndicator
import javafx.scene.layout.BorderPane

final class MainWindowController implements FileMenu, ProjectMenu, AlgorithmsMenu, PropertiesVBox {

    // MenuBar:
    @FXML MenuBar menuBar

    // Panes:
    @FXML BorderPane mainPane

    // Initialization:
    @FXML
    void initialize() {
        Context.ORL_CASE_CONTROLLER.CASES.each { group ->
            Menu menu = new Menu(group.a.toString())
            this.gui_fxml_ProjectMenu__projectNewOrlCaseMenu.items.add(menu)
            group.b.each { caseName ->
                MenuItem item = new MenuItem(caseName)
                item.setOnAction { Context.ORL_CASE_CONTROLLER.loadCase(caseName) }
                menu.items.add(item)
            }
        }
        Context.ALGORITHM_CONTROLLER.algorithms.each { group ->
            Menu menu = new Menu(group.key)
            this.gui_fxml_AlgorithmsMenu__algorithmsMenu.items.add(menu)
            group.value.each { algorithmName ->
                MenuItem item = new MenuItem(algorithmName)
                item.setOnAction { Context.ALGORITHM_CONTROLLER.runAlgorithm(group.key, algorithmName) }
                menu.items.add(item)
            }
        }
    }
}