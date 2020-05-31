package gui.fxml.components.euclidean

import api.Parameters
import javafx.scene.Node
import javafx.scene.layout.AnchorPane
import math.graphs.theory.Vertex
import math.metricspaces.EuclideanPoint

import java.awt.TextField

class EuclideanGraphPane extends AnchorPane {

    static final String GRAPH_PANE_STYLE = """
        -fx-border-color:       ${Parameters.HEX_COLOR_LIGHT_GREY};
        //-fx-background-color: ${Parameters.HEX_COLOR_WHITE};
    """

    private final Map<Vertex, EuclideanVertex> verticesMapping = new HashMap<>()
    private final Set<EuclideanLine> edges = new HashSet<>()

    EuclideanGraphPane() {
        setMaxSize(Parameters.WORK_GROUND_SIDE_SIZE, Parameters.WORK_GROUND_SIDE_SIZE)
        setMinSize(Parameters.WORK_GROUND_SIDE_SIZE, Parameters.WORK_GROUND_SIDE_SIZE)
        setPrefSize(Parameters.WORK_GROUND_SIDE_SIZE, Parameters.WORK_GROUND_SIDE_SIZE)

        setStyle(GRAPH_PANE_STYLE)
    }

    EuclideanVertex newVertex(Vertex graphVertex) {
        EuclideanVertex vertex = new EuclideanVertex(graphVertex.getType())
        vertex.bind((graphVertex.location as EuclideanPoint).x, (graphVertex.location as EuclideanPoint).y)
        verticesMapping.put(graphVertex, vertex)
        children.add(vertex)
        // xProperty.addListener(xListener)
        // yProperty.addListener(yListener)
        vertex
    }

    EuclideanLine newEdge(Vertex vertexA, Vertex vertexB) {
        EuclideanLine line = new EuclideanLine(
                verticesMapping.get(vertexA),
                verticesMapping.get(vertexB)
        )
        children.add(line)
        edges.add(line)
        line
    }

    def clear() {
        edges.each { children.remove(it) }
        edges.clear()
        verticesMapping.each { children.remove(it.value) }
        verticesMapping.clear()
    }
}
