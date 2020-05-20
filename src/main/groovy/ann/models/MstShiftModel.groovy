package ann.models

import ann.Model
import math.graphs.VertexTypes
import math.graphs.theory.Graph
import math.graphs.theory.Vertex
import math.metricspaces.EuclideanPoint
import math.metricspaces.Point
import math.utils.GraphUtils
import math.utils.MetricSpaceUtils

class MstShiftModel implements Model {

    private Graph graph

    final String     name
    final double     shift
    final double[]   hiddenLayer
    final double[][] hiddenLayerWeights

    MstShiftModel(String name, double shift, int inputSize, int hiddenSize) {
        this.name          = name
        this.shift         = shift
        hiddenLayer        = new double[hiddenSize]
        hiddenLayerWeights = new double[inputSize][hiddenSize]
    }

    @Override
    void calculate(Graph graph) {
        this.graph = graph

        GraphUtils.minimalSpanningTree(graph)
        graph.edges.each { addShiftPoints(it.a.location, it.b.location) }
        graph.clearEdges()
        GraphUtils.minimalSpanningTree(graph)

        boolean hasExcess = true
        while (hasExcess) {
            List<Vertex> excessVertices = new LinkedList<>()
            graph.topology.vertices.each {
                if (it.type == VertexTypes.STEINER && it.neighbors.size() != 3) {
                    excessVertices.add(it)
                }
            }
            hasExcess = !excessVertices.isEmpty()
            graph.removeVertices(excessVertices.toArray() as Vertex[])
            GraphUtils.minimalSpanningTree(graph)
        }

        graph.topology.vertices.each {
            if (it.type == VertexTypes.STEINER) {
                it.location = MetricSpaceUtils.of(graph.metricSpace).steinerPointFor(
                        it.neighbors[0].location,
                        it.neighbors[1].location,
                        it.neighbors[2].location
                )
            }
        }
    }

    private final void addShiftPoints(Point a, Point b) {
        Point middle = middle(a, b)
        Point shiftPoint1 = MetricSpaceUtils.of(graph.metricSpace).counterclockwiseRotation(a, middle,  shift)
        Point shiftPoint2 = MetricSpaceUtils.of(graph.metricSpace).counterclockwiseRotation(a, middle, -shift)
        graph.newSteinerPoint(shiftPoint1)
        graph.newSteinerPoint(shiftPoint2)
    }

    private final Point middle(Point a, Point b) {
        if (a instanceof EuclideanPoint && b instanceof EuclideanPoint)
            graph.metricSpace.point(
                (a.x + b.x) / 2,
                (a.y + b.y) / 2
            )
    }
}
