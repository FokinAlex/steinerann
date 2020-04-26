package api.enities.pages

import api.Parameters
import gui.fxml.components.PageContentPane
import gui.fxml.components.euclidean.EuclideanGraphPane
import javafx.scene.Node
import math.graphs.theory.Graph
import math.graphs.theory.Vertex

class OrlCasePage<G extends Graph> extends Page implements AlgorithmFriendlyPage<G>  {

    final G graph
    final G steinerTree
    final PageContentPane content
    final EuclideanGraphPane graphPane
    final EuclideanGraphPane steinerTreePane

    OrlCasePage(String name, G graph, G steinerTree) {
        super(name)
        this.graph = graph
        this.steinerTree = steinerTree
        this.graphPane = new EuclideanGraphPane()
        this.steinerTreePane = new EuclideanGraphPane()
        this.content = initContent()

        refillGraphPane()
        refillSteinerTreePane()
    }

    private PageContentPane initContent() {
        PageContentPane _content = new PageContentPane(
                Parameters.ORL_CASE_PAGE_MAIN_PANE_WIDTH,
                Parameters.ORL_CASE_PAGE_MAIN_PANE_HEIGHT
        )

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

    def refillGraphPane() {
        graphPane.clear()
        graph.topology.vertices.each { graphPane.newVertex(it as Vertex) }
        graph.edges.each { graphPane.newEdge(it.a, it.b) }
    }

    def refillSteinerTreePane() {
        steinerTreePane.clear()
        steinerTree.topology.vertices.each { steinerTreePane.newVertex(it as Vertex) }
        steinerTree.edges.each { steinerTreePane.newEdge(it.a, it.b) }
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
        refillGraphPane()
    }
}
