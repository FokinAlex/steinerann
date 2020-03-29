package api.enities.pages

import javafx.beans.property.SimpleStringProperty
import javafx.scene.Node

abstract class Page {

    final SimpleStringProperty name

    Page (String name) {
        this.name = new SimpleStringProperty(name)
    }

    abstract Node getContent()
}