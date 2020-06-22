package api.enities.pages

import api.Parameters
import gui.fxml.components.PageContentPane
import gui.fxml.components.euclidean.EuclideanGraphPane
import javafx.beans.property.BooleanProperty
import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.control.TextField
import math.algorithms.other.KruskallAlgorithm
import math.graphs.theory.Graph
import math.graphs.theory.Vertex
import math.utils.GraphUtils

class GraphPage<G extends Graph> extends Page implements AlgorithmFriendlyPage<G> {

    final G graph
    final G minimalSpanningTree
    final PageContentPane content
    final EuclideanGraphPane graphPane
    final EuclideanGraphPane minimalSpanningTreePane
    final TextField graphWeightField
    final TextField minimalSpanningTreeWeightField

    GraphPage(String name, G graph) {
        super(name)
        this.graph = graph
        this.minimalSpanningTree = GraphUtils.cloneGraph(graph)
        this.minimalSpanningTreePane = new EuclideanGraphPane()
        this.graphPane = new EuclideanGraphPane()
        this.minimalSpanningTreeWeightField = new TextField()
        this.graphWeightField = new TextField()
        this.content = initContent()

        refillMinimalSpanningTreePane()
        refillGraphPane()
    }

    private PageContentPane initContent() {
        PageContentPane _content = new PageContentPane(
                Parameters.GRAPH_PAGE_MAIN_PANE_WIDTH,
                Parameters.GRAPH_PAGE_MAIN_PANE_HEIGHT
        )

        _content.addNode(
                minimalSpanningTreePane,
                Parameters.WORK_GROUND_BORDER_SIZE,
                Parameters.WORK_GROUND_BORDER_SIZE
        )

        _content.addNode(
                graphPane,
                Parameters.WORK_GROUND_BORDERED_SIDE_SIZE,
                Parameters.WORK_GROUND_BORDER_SIZE
        )

        minimalSpanningTreeWeightField.setPrefSize(Parameters.WORK_GROUND_SIDE_SIZE, 20)
        minimalSpanningTreeWeightField.disableProperty().set(true)
        minimalSpanningTreeWeightField.alignmentProperty().set(Pos.BASELINE_CENTER)
        _content.addNode(
                minimalSpanningTreeWeightField,
                Parameters.WORK_GROUND_BORDER_SIZE,
                Parameters.WORK_GROUND_BORDERED_SIDE_SIZE
        )

        graphWeightField.setPrefSize(Parameters.WORK_GROUND_SIDE_SIZE, 20)
        graphWeightField.disableProperty().set(true)
        graphWeightField.alignmentProperty().set(Pos.BASELINE_CENTER)
        _content.addNode(
                graphWeightField,
                Parameters.WORK_GROUND_BORDERED_SIDE_SIZE,
                Parameters.WORK_GROUND_BORDERED_SIDE_SIZE
        )

        _content
    }

    def refillMinimalSpanningTreePane() {
        new KruskallAlgorithm(minimalSpanningTree).run()
        minimalSpanningTree.topology.vertices.each { minimalSpanningTreePane.newVertex(it as Vertex) }
        minimalSpanningTree.edges.each { minimalSpanningTreePane.newEdge(it.a, it.b) }
        minimalSpanningTreeWeightField.setText(minimalSpanningTree.weight as String)
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
