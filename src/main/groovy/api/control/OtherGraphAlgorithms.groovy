package api.control

import math.algorithms.Algorithm
import math.algorithms.graph.KruskallAlgorithm
import math.graphs.theory.Graph

// TODO: Observer
enum OtherGraphAlgorithms implements AlgorithmType<Graph> {
    // TODO: replace with param
    KRUSKALL_ALGORITHM("Kruskall Algorithm") {

        @Override
        Algorithm getInstance(Graph graph) {
            new KruskallAlgorithm(graph)
        }

    };

    final String name

    OtherGraphAlgorithms(String name) {
        this.name = name
    }

    @Override
    void run(Graph graph) {
        // TODO: log
        Algorithm algorithm = this.getInstance(graph)
        println "start ${algorithm}"
        Thread thread = new Thread(algorithm)
        thread.start()
        println "finish ${algorithm}"
    }

    // TODO: ???
    abstract Algorithm<Graph> getInstance(Graph graph)
}
