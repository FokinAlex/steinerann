package math.graphs.theory.abstractions

import identification.Sequence
import math.graphs.theory.GraphTheoryEntity
import math.metricspaces.MetricSpace

abstract class AbstractGraph<V extends AbstractVertex, E extends AbstractEdge<V>> implements GraphTheoryEntity {

    protected final MetricSpace metricSpace

    protected final Sequence vSequnce = new Sequence()
    protected final Sequence eSequnce = new Sequence()

    final Set<V> vertexes = new HashSet<>()
    final Set<E> edges = new HashSet<>()

    AbstractGraph(MetricSpace metricSpace) {
        this.metricSpace = metricSpace
    }

    boolean addVertex(V vertex) {
        vertexes.add(vertex)
    }

    boolean removerVertex(V vertex) {
        edges.each {
            if (it.contains(vertex)) removeEdge(it)
        }
        vertexes.remove(vertex)
    }

    boolean addEdge(E edge) {
        if (edge) {
            edges.each {
                if (it.contains(edge.first) && it.contains(edge.second)) return false
            }
        }
        edges.add(edge)
    }

     boolean removeEdge(E edge) {
        edges.remove(edge)
    }

    Sequence getVertexSequnce() {
        return vSequnce
    }

    Sequence getEdgeSequnce() {
        return eSequnce
    }

    double getWeight() {
        // TODO:
        // edges.isEmpty() ? 0 : edges.stream()
        //         .map(AbstractEdge.&getWeight())
        //         .reduce({a, b -> a + b})
        //         .get() as double
    }
}
