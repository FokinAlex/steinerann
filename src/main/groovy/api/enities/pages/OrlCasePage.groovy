package api.enities.pages

import api.Parameters
import gui.fxml.components.PageContentPane
import gui.fxml.components.euclidean.EuclideanGraphPane
import javafx.scene.Node
import math.graphs.VertexTypes
import math.graphs.theory.Graph
import math.graphs.theory.Vertex

class OrlCasePage<G extends Graph> extends Page {

    final G graph
    final PageContentPane content

    OrlCasePage(String name, Graph graph) {
        super(name)
        this.graph = graph
        this.content = initContent()
    }

    private PageContentPane initContent() {
        PageContentPane _content = new PageContentPane(Parameters.ORL_CASE_PAGE_MAIN_PANE_WIDTH, Parameters.ORL_CASE_PAGE_MAIN_PANE_HEIGHT)
        EuclideanGraphPane graphPane = new EuclideanGraphPane()
        EuclideanGraphPane steinerTreePane = new EuclideanGraphPane()

        graph.topology.vertices.each {
            steinerTreePane.newVertex(it as Vertex)
            if (it.type == VertexTypes.SIMPLE) {
                graphPane.newVertex(it as Vertex)
            }
        }
        graph.edges.each {
            steinerTreePane.newEdge(it.a, it.b)
        }
        _content.addNode(
                graphPane,
                Parameters.WORK_GROUND_BORDER_SIZE,
                Parameters.WORK_GROUND_BORDER_SIZE
        )
        _content.addNode(
                steinerTreePane,
                Parameters.WORK_GROUND_BORDERED_SIDE_SIZE,
                Parameters.WORK_GROUND_BORDER_SIZE
        )
        _content
    }

    @Override
    Node getContent() {
        content
    }
}
