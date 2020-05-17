package gui.fxml.components.euclidean

import api.Parameters
import javafx.scene.Group
import javafx.scene.shape.Line

// TODO: inner-class
class EuclideanLine extends Group {

    private final Line body
    private final EuclideanVertex a
    private final EuclideanVertex b

    // TODO: selectable? with rectangle : without rectangle

    EuclideanLine(EuclideanVertex a, EuclideanVertex b) {
        this.body = new Line()
        this.a = a
        this.b = b
        getChildren().add(body)
        body.startXProperty().bind(a.layoutXProperty().add(a.radius))
        body.startYProperty().bind(a.layoutYProperty().add(a.radius))
        body.endXProperty().bind(b.layoutXProperty().add(b.radius))
        body.endYProperty().bind(b.layoutYProperty().add(b.radius))
        body.setStyle("-fx-fill: ${Parameters.HEX_COLOR_DARK_GREY};")
    }
}
