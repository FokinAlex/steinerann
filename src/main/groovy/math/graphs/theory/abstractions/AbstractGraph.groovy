package math.graphs.theory.abstractions

import identification.Sequence
import math.graphs.theory.GraphTheoryEntity
import math.metricspaces.MetricSpace

abstract class AbstractGraph<V extends AbstractVertex, E extends AbstractEdge<V>> implements GraphTheoryEntity {

    protected final MetricSpace metricSpace

    protected final Sequence vSequnce = new Sequence()
    protected final Sequence eSequnce = new Sequence()

    protected Set<V> vertexes = new HashSet<>()
    protected Set<E> edges = new HashSet<>()

    AbstractGraph(MetricSpace metricSpace) {
        this.metricSpace = metricSpace
    }

    protected boolean addVertex(V vertex) {
        vertexes.add(vertex)
    }

    protected boolean removerVertex(V vertex) {
        edges.each {
            if (it.contains(vertex)) removeEdge(it)
        }
        vertexes.remove(vertex)
    }

    protected boolean addEdge(E edge) {
        if (edge) {
            edges.each {
                if (it.contains(edge.first) && it.contains(edge.second)) return false
            }
        }
        edges.add(edge)
    }

    protected boolean removeEdge(E edge) {
        edges.remove(edge)
    }

    double getWeight() {
        // TODO:
        // edges.isEmpty() ? 0 : edges.stream()
        //         .map(AbstractEdge.&getWeight())
        //         .reduce({a, b -> a + b})
        //         .get() as double
    }
}
