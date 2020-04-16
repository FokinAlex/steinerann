package api.enities.pages

import gui.fxml.components.GraphPane
import javafx.scene.Node
import math.graphs.theory.Graph
import math.graphs.theory.Vertex

class GraphPage<G extends Graph> extends Page {

    final G graph
    final GraphPane content

    GraphPage(String name, G graph) {
        super(name)
        this.graph = graph
        this.content = initContent()
    }

    private GraphPane initContent() {
        GraphPane pane = new GraphPane()
        graph.topology.vertices.each {
            pane.newVertex(it as Vertex)
        }
        pane
    }

    @Override
    Node getContent() {
        content
    }
}
