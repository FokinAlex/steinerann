package api.enities.pages

import api.Parameters
import gui.fxml.components.PageContentPane
import gui.fxml.components.euclidean.EuclideanGraphPane
import javafx.beans.property.BooleanProperty
import javafx.scene.Node
import math.graphs.theory.Graph
import math.graphs.theory.Vertex

class GraphPage<G extends Graph> extends Page implements AlgorithmFriendlyPage<G> {

    final G graph
    final PageContentPane content
    final EuclideanGraphPane graphPane

    GraphPage(String name, G graph) {
        super(name)
        this.graph = graph
        this.graphPane = new EuclideanGraphPane()
        this.content = initContent()
    }

    private PageContentPane initContent() {
        PageContentPane _content = new PageContentPane(Parameters.GRAPH_PAGE_MAIN_PANE_WIDTH, Parameters.GRAPH_PAGE_MAIN_PANE_HEIGHT)

        _content.addNode(
                graphPane,
                Parameters.WORK_GROUND_BORDER_SIZE,
                Parameters.WORK_GROUND_BORDER_SIZE
        )

        _content
    }

    def refillGraphPane() {
        graphPane.clear()
        graph.topology.vertices.each { graphPane.newVertex(it as Vertex) }
        graph.edges.each { graph.newEdge(it.a, it.b) }
    }

    @Override
    Node getContent() {
        content
    }

    @Override
    G getArgument() {
        graph
    }

    @Override
    def update() {
        return null
    }
}
