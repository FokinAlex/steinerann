package gui.fxml.components

import gui.Parameters
import javafx.beans.property.DoubleProperty
import javafx.scene.layout.StackPane
import javafx.scene.shape.Circle
import math.graphs.VertexType

class EuclideanVertex extends StackPane {

    private Circle body

    EuclideanVertex(DoubleProperty x, DoubleProperty y, VertexType type) {

        body = new Circle(type == VertexType.STEINER ? Parameters.STEINER_VERTEX_RADIUS : Parameters.SIMPLE_VERTEX_RADIUS)
        body.layoutXProperty().set(body.radius)
        body.layoutYProperty().set(body.radius)
        body.setStyle("-fx-fill: ${Parameters.HEX_COLOR_DARK_GREY};")

        this.layoutXProperty().set(x.get() - body.radius)
        this.layoutYProperty().set(y.get() - body.radius)
        // this.layoutXProperty().bind(x)
        // this.layoutYProperty().bind(y)

        this.getChildren().add(body)

    }
}
