package api.enities.pages

import gui.fxml.components.euclidean.EuclideanGraphPane
import javafx.scene.Node
import math.graphs.theory.Graph
import math.graphs.theory.Vertex

class GraphPage<G extends Graph> extends Page {

    final G graph
    final EuclideanGraphPane content

    GraphPage(String name, G graph) {
        super(name)
        this.graph = graph
        this.content = initContent()
    }

    private EuclideanGraphPane initContent() {
        EuclideanGraphPane pane = new EuclideanGraphPane()
        graph.topology.vertices.each {
            pane.newVertex(it as Vertex)
        }
        graph.edges.each {
            pane.newEdge(it.a, it.b)
        }
        pane
    }

    @Override
    Node getContent() {
        content
    }
}
