package gui.fxml.components

import javafx.scene.control.TabPane

class ProjectPane extends TabPane {

    def plus(PageTab pageTab) {
        this.getTabs().add(pageTab)
    }
}
