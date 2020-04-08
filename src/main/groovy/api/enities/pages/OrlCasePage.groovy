package api.enities.pages

import math.graphs.theory.abstractions.AbstractGraph

class OrlCasePage<Graph extends AbstractGraph> extends GraphPage<Graph> {

    OrlCasePage(String name, Graph graph) {
        super(name, graph)
    }
}
