package api.enities.pages

import api.Parameters
import gui.fxml.components.PageContentPane
import gui.fxml.components.euclidean.EuclideanGraphPane
import javafx.scene.Node
import math.graphs.theory.Graph
import math.graphs.theory.Vertex

class GraphPage<G extends Graph> extends Page {

    final G graph
    final PageContentPane content

    GraphPage(String name, G graph) {
        super(name)
        this.graph = graph
        this.content = initContent()
    }

    private PageContentPane initContent() {
        PageContentPane _content = new PageContentPane(Parameters.GRAPH_PAGE_MAIN_PANE_WIDTH, Parameters.GRAPH_PAGE_MAIN_PANE_HEIGHT)
        // TODO: add graph pane
        EuclideanGraphPane graphPane = new EuclideanGraphPane()
        graph.topology.vertices.each {
            graphPane.newVertex(it as Vertex)
        }
        graph.edges.each {
            graph.newEdge(it.a, it.b)
        }
        _content.addNode(graphPane, Parameters.WORK_GROUND_BORDER_SIZE, Parameters.WORK_GROUND_BORDER_SIZE)
        _content
    }

    @Override
    Node getContent() {
        content
    }
}
