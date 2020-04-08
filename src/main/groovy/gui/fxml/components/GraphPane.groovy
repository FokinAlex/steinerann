package gui.fxml.components

import gui.Parameters
import javafx.beans.property.DoubleProperty
import javafx.beans.property.SimpleDoubleProperty
import javafx.scene.control.Label
import javafx.scene.control.ScrollPane
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.Pane
import math.graphs.theory.abstractions.AbstractVertex
import math.metricspaces.EuclideanPoint

class GraphPane extends ScrollPane {

    static final String GRAPH_PANE_STYLE = """
        -fx-border-color:     ${Parameters.HEX_COLOR_LIGHT_GREY};
        //-fx-background-color: ${Parameters.HEX_COLOR_WHITE};
    """

    final AnchorPane mainPane

    final Pane graphPane
    // final Label zeroLabel
    // final Label oneXLabel
    // final Label oneYLabel

    GraphPane() {
        mainPane = new AnchorPane()
        mainPane.setMaxSize(Parameters.MAIN_PANE_WIDTH, Parameters.MAIN_PANE_HEIGHT)
        mainPane.setMinSize(Parameters.MAIN_PANE_WIDTH, Parameters.MAIN_PANE_HEIGHT)
        mainPane.setPrefSize(Parameters.MAIN_PANE_WIDTH, Parameters.MAIN_PANE_HEIGHT)

        graphPane = new AnchorPane()
        graphPane.setMaxSize(Parameters.WORK_GROUND_SIDE_SIZE, Parameters.WORK_GROUND_SIDE_SIZE)
        graphPane.setMinSize(Parameters.WORK_GROUND_SIDE_SIZE, Parameters.WORK_GROUND_SIDE_SIZE)
        graphPane.setPrefSize(Parameters.WORK_GROUND_SIDE_SIZE, Parameters.WORK_GROUND_SIDE_SIZE)
        graphPane.setStyle(GRAPH_PANE_STYLE)

        // zeroLabel = new Label("0")
        // oneXLabel = new Label("1")
        // oneYLabel = new Label("1")

        mainPane.getChildren().add(graphPane)
        mainPane.setTopAnchor(graphPane, Parameters.WORK_GROUND_BORDER_SIZE)
        mainPane.setLeftAnchor(graphPane, Parameters.WORK_GROUND_BORDER_SIZE)
        // mainPane.getChildren().addAll(zeroLabel, oneXLabel, oneYLabel)
        // mainPane.setTopAnchor(zeroLabel, Parameters.WORK_GROUND_BORDER_SIZE)
        // mainPane.setLeftAnchor(zeroLabel, Parameters.WORK_GROUND_BORDER_SIZE)
        // mainPane.setTopAnchor(oneXLabel, Parameters.WORK_GROUND_BORDER_SIZE)
        // mainPane.setLeftAnchor(oneXLabel, Parameters.WORK_GROUND_BORDER_SIZE + Parameters.WORK_GROUND_SIDE_SIZE)
        // mainPane.setTopAnchor(oneYLabel, Parameters.WORK_GROUND_BORDER_SIZE  + Parameters.WORK_GROUND_SIDE_SIZE)
        // mainPane.setLeftAnchor(oneYLabel, Parameters.WORK_GROUND_BORDER_SIZE)

        this.content = mainPane
    }

    // private final Map<Sos, Duo<DoubleProperty, DoubleProperty> sos

    def newVertex(AbstractVertex graphVertex) {
        DoubleProperty xProperty = new SimpleDoubleProperty((graphVertex.getLocation() as EuclideanPoint).x * Parameters.SCALE_MULTIPLIER)
        DoubleProperty yProperty = new SimpleDoubleProperty((graphVertex.getLocation() as EuclideanPoint).y * Parameters.SCALE_MULTIPLIER)
        EuclideanVertex vertex = new EuclideanVertex(xProperty, yProperty, graphVertex.getType())
        graphPane.getChildren().add(vertex)

        // xProperty.addListener(xListener)
        // yProperty.addListener(yListener)
    }

//    def xListener = { observable, oldValue, newValue ->
//        observable = (EuclideanPoint) observable
//        observable.x = newValue
//    }
//
//    def yListener = { observable, oldValue, newValue ->
//        observable = (EuclideanPoint) observable
//        observable.y = newValue
//    }

}
