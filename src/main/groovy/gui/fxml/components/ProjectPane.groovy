package gui.fxml.components

import gui.fxml.components.tabs.AbstractTab
import javafx.scene.control.TabPane

class ProjectPane extends TabPane {

    def plus(AbstractTab pageTab) {
        this.getTabs().add(pageTab)
    }
}
