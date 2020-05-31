package api.enities.pages

import api.Parameters
import gui.fxml.components.PageContentPane
import gui.fxml.components.euclidean.EuclideanGraphPane
import javafx.beans.property.BooleanProperty
import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.control.TextField
import math.graphs.theory.Graph
import math.graphs.theory.Vertex

class GraphPage<G extends Graph> extends Page implements AlgorithmFriendlyPage<G> {

    final G graph
    final PageContentPane content
    final EuclideanGraphPane graphPane
    final TextField graphWeightField

    GraphPage(String name, G graph) {
        super(name)
        this.graph = graph
        this.graphPane = new EuclideanGraphPane()
        this.graphWeightField = new TextField("0.0")
        this.content = initContent()

        refillGraphPane()
    }

    private PageContentPane initContent() {
        PageContentPane _content = new PageContentPane(
                Parameters.GRAPH_PAGE_MAIN_PANE_WIDTH,
                Parameters.GRAPH_PAGE_MAIN_PANE_HEIGHT
        )

        _content.addNode(
                graphPane,
                Parameters.WORK_GROUND_BORDER_SIZE,
                Parameters.WORK_GROUND_BORDER_SIZE
        )

        graphWeightField.setPrefSize(Parameters.WORK_GROUND_SIDE_SIZE, 20)
        graphWeightField.disableProperty().set(true)
        graphWeightField.alignmentProperty().set(Pos.BASELINE_CENTER)
        _content.addNode(graphWeightField, Parameters.WORK_GROUND_BORDER_SIZE, Parameters.WORK_GROUND_BORDERED_SIDE_SIZE)

        _content
    }

    def refillGraphPane() {
        graphPane.clear()
        graph.topology.vertices.each { graphPane.newVertex(it as Vertex) }
        graph.edges.each { graphPane.newEdge(it.a, it.b) }
        graphWeightField.setText(graph.weight as String)
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
