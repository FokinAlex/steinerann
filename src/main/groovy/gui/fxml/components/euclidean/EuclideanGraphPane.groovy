package gui.fxml.components.euclidean

import gui.Parameters
import javafx.scene.control.ScrollPane
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.Pane
import math.graphs.theory.Vertex
import math.metricspaces.EuclideanPoint

class EuclideanGraphPane extends ScrollPane {

    static final String GRAPH_PANE_STYLE = """
        -fx-border-color:     ${Parameters.HEX_COLOR_LIGHT_GREY};
        //-fx-background-color: ${Parameters.HEX_COLOR_WHITE};
    """

    final AnchorPane mainPane
    final Pane graphPane

    private final Map<Vertex, EuclideanVertex> vertices = new HashMap<>()

    EuclideanGraphPane() {
        mainPane = new AnchorPane()
        mainPane.setMaxSize(Parameters.MAIN_PANE_WIDTH, Parameters.MAIN_PANE_HEIGHT)
        mainPane.setMinSize(Parameters.MAIN_PANE_WIDTH, Parameters.MAIN_PANE_HEIGHT)
        mainPane.setPrefSize(Parameters.MAIN_PANE_WIDTH, Parameters.MAIN_PANE_HEIGHT)

        graphPane = new AnchorPane()
        graphPane.setMaxSize(Parameters.WORK_GROUND_SIDE_SIZE, Parameters.WORK_GROUND_SIDE_SIZE)
        graphPane.setMinSize(Parameters.WORK_GROUND_SIDE_SIZE, Parameters.WORK_GROUND_SIDE_SIZE)
        graphPane.setPrefSize(Parameters.WORK_GROUND_SIDE_SIZE, Parameters.WORK_GROUND_SIDE_SIZE)
        graphPane.setStyle(GRAPH_PANE_STYLE)

        mainPane.getChildren().add(graphPane)
        mainPane.setTopAnchor(graphPane, Parameters.WORK_GROUND_BORDER_SIZE)
        mainPane.setLeftAnchor(graphPane, Parameters.WORK_GROUND_BORDER_SIZE)

        this.content = mainPane
    }

    EuclideanVertex newVertex(Vertex graphVertex) {
        EuclideanVertex vertex = new EuclideanVertex(graphVertex.getType())
        vertex.bind((graphVertex.location as EuclideanPoint).x, (graphVertex.location as EuclideanPoint).y)
        graphPane.getChildren().add(vertex)
        vertices.put(graphVertex, vertex)
        // xProperty.addListener(xListener)
        // yProperty.addListener(yListener)
        vertex
    }

    EuclideanLine newEdge(Vertex vertexA, Vertex vertexB) {
        EuclideanLine line = new EuclideanLine(
                vertices.get(vertexA),
                vertices.get(vertexB)
        )
        graphPane.getChildren().add(line)
        line
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
