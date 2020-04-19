package gui.fxml.components.euclidean

import api.Parameters
import javafx.beans.property.DoubleProperty
import javafx.scene.layout.StackPane
import javafx.scene.shape.Circle
import math.graphs.VertexTypes

class EuclideanVertex extends StackPane {

    private Circle body

    EuclideanVertex(VertexTypes type) {
        body = new Circle(type == VertexTypes.STEINER ? Parameters.STEINER_VERTEX_RADIUS : Parameters.SIMPLE_VERTEX_RADIUS)
        body.layoutXProperty().set(body.radius)
        body.layoutYProperty().set(body.radius)
        body.setStyle("-fx-fill: ${Parameters.HEX_COLOR_DARK_GREY};")
        this.getChildren().add(body)
    }

    def bind(DoubleProperty xProperty, DoubleProperty yProperty) {
        this.layoutXProperty().set(xProperty.get() * Parameters.SCALE_MULTIPLIER - body.radius)
        this.layoutYProperty().set(yProperty.get() * Parameters.SCALE_MULTIPLIER - body.radius)
//        this.layoutXProperty().bind((xProperty * Parameters.SCALE_MULTIPLIER).subtract(body.radiusProperty()))
//        this.layoutYProperty().bind((yProperty * Parameters.SCALE_MULTIPLIER).subtract(body.radiusProperty()))
    }

    DoubleProperty getRadius() {
        body.radiusProperty()
    }
}
