package math.algorithms

import log.LogFacade
import math.graphs.theory.Graph

// TODO: Observable
abstract class AbstractGraphAlgorithm<G extends Graph> implements Algorithm<G> {

    final G graph

    private final String name

    AbstractGraphAlgorithm(G graph, String name) {
        this.graph = graph
        this.name = name
    }

    def logStep(String message) {
        LogFacade.INFO("${name} [${this.hashCode()}] ${message}")
    }

    @Override
    def step() {
        // TODO: make a record
        return null
    }

    @Override
    def completed() {
        // TODO: notify all listeners
        // changeListeners.each { it.changed(this, false, true) }
        // invalidationListeners.each { it.invalidated(this) }
    }
}
