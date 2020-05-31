package ann

import trash.DistanceAnglePerceptronModel
import math.graphs.VertexTypes
import math.graphs.theory.Graph
import math.graphs.theory.Vertex
import math.utils.GraphUtils
import math.utils.MetricSpaceUtils
import utils.orl.OrlCaseLoader
import utils.others.Duo
import utils.others.Triple

final class AnnTeacher {

    static final Set<String> ORL_CASES = OrlCaseLoader.getFlatRawCases()

    // static final Duo<Graph, Graph> sas(String caseName) {
    //     OrlCaseLoader.loadCase(caseName)
    // }

    static final double trainWithOrl(DistanceAnglePerceptronModel model, double percentOfTests) {
        List<String> learnCases = [ ]
        List<String> testCases  = [ ]
        ORL_CASES.each {
            Math.random() > percentOfTests ? learnCases.add(it) : testCases.add(it)
        }

        0d
    }

    static final double testAccuracy(DistanceAnglePerceptronModel model) {
        innerTestAccuracy(model, getRandomCase())
    }

    static final double testAccuracy(DistanceAnglePerceptronModel model, String testCaseName) {
        innerTestAccuracy(model, OrlCaseLoader.loadCase(testCaseName))
    }

    static final double innerTestAccuracy(DistanceAnglePerceptronModel model, Duo<Graph, Graph> testCase) {

        // 1. Solve problem with model
        final Map<Vertex, Duo<Double, Double>> modelResults = solve(model, testCase.a)

        // 2. Get right solution
        final Map<Vertex, Triple<Double, Double, Vertex>> rightSolutions = rightSolution(testCase.b)

        if (modelResults.size() != rightSolutions.size()) throw new IllegalArgumentException("Solutions have different sizes")

        // 3. Calculate accuracy
        double accuracy = 0
        modelResults.each { modelResult ->
            Triple<Double, Double, Vertex> rightSolution = rightSolutions.get(modelResult.key)
            if (!rightSolution) throw new IllegalArgumentException("Have no solution")
            accuracy += Math.abs(
                    testCase.a.metricSpace.metric(
                            MetricSpaceUtils.EUCLIDEAN.getPointByVector(
                                    modelResult.key.location,
                                    modelResult.value.a,
                                    modelResult.value.b
                            ),
                            rightSolution.c.location
                    ) as double
            )
        }
        accuracy
    }

    /**
     * @param   model
     * @param   graph
     *
     * @return  for each Vertex pair [distance, directionAngle] of nearest Steiner point
     */
    static final Map<Vertex, Duo<Double, Double>> solve(DistanceAnglePerceptronModel model, Graph graph) {
        GraphUtils.makeComplete(graph)
        Map<Vertex, Duo<Double, Double>> modelResults = new HashMap<>()
        graph.topology.vertices.each { base ->
            List<Duo<Double, Double>> values = new LinkedList<>()
            base.neighbors.each { target ->
                values.add(new Duo<Double, Double>(
                        a: graph.metricSpace.metric(base.location, target.location),
                        b: MetricSpaceUtils.EUCLIDEAN.directionAngle(base.location, target.location)
                ))
            }
            values.sort { it.a }
            double[] distances = values.collect { it.a }
            double[] angles    = values.collect { it.b }
            modelResults.put(base, model.run(distances, angles))
        }
        modelResults
    }

    // Right solution by nearest Steiner point
    // ? ? ? nearest point
    // ? ? ? connected steiner point
    // ? ? ? by rules
    static Map<Vertex, Triple<Double, Double, Vertex>> rightSolution(Graph steinerTree) {
        Map<Vertex, Triple<Double, Double, Vertex>> rightSolution = new HashMap<>()
        steinerTree.topology.vertices.each { vertex ->
            if (vertex.type != VertexTypes.STEINER) {
                List<Vertex> steinerPoints = new LinkedList<>(steinerTree.steinerPoints)
                if (steinerPoints.isEmpty()) {
                    // TODO: ?
                    return rightSolution
                }
                Vertex nearestSteinerPoint = null
                double nearestSteinerPointDistance = Double.MAX_VALUE
                double steinerPointDistance
                steinerPoints.each { steinerPoint ->
                    steinerPointDistance = steinerTree.metricSpace.metric(vertex.location, steinerPoint.location)
                    if (steinerPointDistance < nearestSteinerPointDistance) {
                        nearestSteinerPoint         = steinerPoint
                        nearestSteinerPointDistance = steinerPointDistance
                    }
                }
                rightSolution.put(
                        vertex,
                        new Triple<Double, Double, Vertex>(
                                a: nearestSteinerPointDistance,
                                b: MetricSpaceUtils.EUCLIDEAN.directionAngle(vertex.location, nearestSteinerPoint.location),
                                c: nearestSteinerPoint
                        )
                )
            }
        }
        rightSolution
    }

    static final Duo<Graph, Graph> getRandomCase() {
        OrlCaseLoader.loadCase(ORL_CASES[Math.random() * ORL_CASES.size() as int])
    }
}
