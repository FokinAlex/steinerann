package api.enities.pages

import gui.fxml.components.GraphPane
import javafx.scene.Node
import math.graphs.theory.abstractions.AbstractGraph
import math.graphs.theory.abstractions.AbstractVertex

class GraphPage<Graph extends AbstractGraph> extends Page {

    final Graph graph
    final GraphPane content

    GraphPage(String name, Graph graph) {
        super(name)
        this.graph = graph
        this.content = initContent()
    }

    private GraphPane initContent() {
        GraphPane pane = new GraphPane()
        graph.getVertexes().each {
            pane.newVertex(it as AbstractVertex)
        }
        pane
    }

    @Override
    Node getContent() {
        content
    }
}
