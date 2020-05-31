package api.enities.pages

import api.Parameters
import gui.fxml.components.PageContentPane
import gui.fxml.components.euclidean.EuclideanGraphPane
import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.control.TextField
import math.graphs.theory.Graph
import math.graphs.theory.Vertex

class OrlCasePage<G extends Graph> extends Page implements AlgorithmFriendlyPage<G>  {

    final G graph
    final G steinerTree
    final PageContentPane content
    final EuclideanGraphPane graphPane
    final EuclideanGraphPane steinerTreePane
    final TextField graphWeightFileld
    final TextField steinerTreeWeightFileld



    OrlCasePage(String name, G graph, G steinerTree) {
        super(name)
        this.graph = graph
        this.steinerTree = steinerTree
        this.graphPane = new EuclideanGraphPane()
        this.steinerTreePane = new EuclideanGraphPane()
        this.graphWeightFileld = new TextField("0.0")
        this.steinerTreeWeightFileld = new TextField(steinerTree.weight as String)
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

        graphWeightFileld.setPrefSize(Parameters.WORK_GROUND_SIDE_SIZE, 20)
        graphWeightFileld.disableProperty().set(true)
        graphWeightFileld.alignmentProperty().set(Pos.BASELINE_CENTER)
        _content.addNode(graphWeightFileld, Parameters.WORK_GROUND_BORDER_SIZE, Parameters.WORK_GROUND_BORDERED_SIDE_SIZE)

        steinerTreeWeightFileld.setPrefSize(Parameters.WORK_GROUND_SIDE_SIZE, 20)
        steinerTreeWeightFileld.disableProperty().set(true)
        steinerTreeWeightFileld.alignmentProperty().set(Pos.BASELINE_CENTER)
        _content.addNode(steinerTreeWeightFileld, Parameters.WORK_GROUND_BORDERED_SIDE_SIZE, Parameters.WORK_GROUND_BORDERED_SIDE_SIZE)

        _content
    }

    def refillGraphPane() {
        graphPane.clear()
        graph.topology.vertices.each { graphPane.newVertex(it as Vertex) }
        graph.edges.each { graphPane.newEdge(it.a, it.b) }
        graphWeightFileld.setText(graph.weight as String)
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
