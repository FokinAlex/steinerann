package gui.fxml

import gui.Context
import javafx.fxml.FXML
import javafx.scene.control.Menu
import javafx.scene.control.MenuBar
import javafx.scene.control.MenuItem
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
    void initialize() {
        Context.ORL_CASE_CONTROLLER.getCases().each { group ->
            Menu menu = new Menu(group.a)
            this.gui_fxml_ProjectMenu__projectNewOrlCaseMenu.getItems().add(menu)
            group.b.each { caseName ->
                MenuItem item = new MenuItem(caseName)
                item.setOnAction { Context.ORL_CASE_CONTROLLER.loadCase(caseName) }
                menu.getItems().add(item)
            }
        }
    }
}