package gui.fxml.components.tabs

import api.enities.pages.Page
import javafx.scene.control.Tab

abstract class AbstractTab<P extends Page> extends Tab {

    final P page

    AbstractTab(String name, P page) {
        super(name)
        this.page = page
        this.setContent(this.page.getContent())
        this.textProperty().bindBidirectional(this.page.getName())
    }
}
