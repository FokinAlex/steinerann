package utils.orl

import api.ProjectFiles
import math.graphs.theory.Graph
import math.metricspaces.MetricSpace
import utils.others.Duo
import utils.others.Quad

final class OrlCaseLoader {

    private static final Map<Integer, List<Duo<String, File>>> GROUPED_CASES = new LinkedHashMap<>()
    static final Map<String, File> CASES = new HashMap<>()

    static {
        findCases(new File(ProjectFiles.ORL_CASES_DIRECTORY))
    }

    static List<Duo<Integer, List<String>>> getCases() {
        List<Duo<Integer, List<String>>> result = new LinkedList<>()
        GROUPED_CASES.sort { it.key }.each { entry ->
            result.add(
                new Duo(
                    a: entry.key,
                    b: entry.value.stream().map { it.a }.collect().toList()
                )
            )
        }
        result
    }

    static Set<String> getFlatRawCases() {
        CASES.keySet()
    }

    static void findCases(File casesDirectory) {
        if (casesDirectory.isDirectory()) {
            Map<String, List<Duo<String, File>>> cases = new LinkedHashMap<>()
            casesDirectory.eachFile { groupDirectory ->
                List<Duo<String, File>> group = new LinkedList<>()
                groupDirectory.eachFile {
                    group.add(new Duo(a: it.getName(), b: it))
                    CASES.put(it.getName(), it)
                }
                GROUPED_CASES.put(Integer.valueOf(groupDirectory.getName()), group)
            }
        }
        GROUPED_CASES.each { group ->
            group.value.sort { Integer.valueOf(it.a.replaceAll(~'([0-9]+-|[.orl]+)', '')) }
        }
    }

    static Duo<Graph, Graph> loadCase(String caseName) {
        File resource = CASES.get(caseName)

        Graph graph = new Graph(MetricSpace.EUCLIDEAN)
        Graph steinerTree = new Graph(MetricSpace.EUCLIDEAN)

        int count
        String[] values
        def x
        def y
        resource.withReader { reader ->
            // TODO: Save it
            reader.readLine() // save Smt
            reader.readLine() // save Mst
            count = Integer.valueOf(reader.readLine())
            for (int ignore = 0; ignore < count; ignore++) {
                values = reader.readLine().split(' ')
                x = Double.valueOf(values[1])
                y = Double.valueOf(values[2])
                steinerTree.newVertex(MetricSpace.EUCLIDEAN.point(x, y))
                graph.newVertex(MetricSpace.EUCLIDEAN.point(x, y))
            }
            count = Integer.valueOf(reader.readLine())
            for (int ignore = 0; ignore < count; ignore++) {
                values = reader.readLine().trim().split(' ')
                x = Double.valueOf(values[1])
                y = Double.valueOf(values[2])
                steinerTree.newSteinerPoint(MetricSpace.EUCLIDEAN.point(x, y))
            }
            count = Integer.valueOf(reader.readLine())
            for (int ignore = 0; ignore < count; ignore++) {
                values = reader.readLine().trim().split(' ')
                x = Integer.valueOf(values[0])
                y = Integer.valueOf(values[1])
                steinerTree.newEdge(
                    steinerTree.topology.vertices.find { it.hashCode() == x },
                    steinerTree.topology.vertices.find { it.hashCode() == y }
                )
            }
        }
        new Duo<>(a: graph, b: steinerTree)
    }

    static Quad<Graph, Graph, Double, Double> loadTask(String caseName) {
        File resource = CASES.get(caseName)

        Graph graph = new Graph(MetricSpace.EUCLIDEAN)
        Graph steinerTree = new Graph(MetricSpace.EUCLIDEAN)
        double minimalSpanningTree
        double minimalSteinerTree

        int count
        String[] values
        def x
        def y
        resource.withReader { reader ->
            // TODO: Save it
            minimalSpanningTree = Double.valueOf(reader.readLine())
            minimalSteinerTree = Double.valueOf(reader.readLine())
            count = Integer.valueOf(reader.readLine())
            for (int ignore = 0; ignore < count; ignore++) {
                values = reader.readLine().split(' ')
                x = Double.valueOf(values[1])
                y = Double.valueOf(values[2])
                steinerTree.newVertex(MetricSpace.EUCLIDEAN.point(x, y))
                graph.newVertex(MetricSpace.EUCLIDEAN.point(x, y))
            }
            count = Integer.valueOf(reader.readLine())
            for (int ignore = 0; ignore < count; ignore++) {
                values = reader.readLine().trim().split(' ')
                x = Double.valueOf(values[1])
                y = Double.valueOf(values[2])
                steinerTree.newSteinerPoint(MetricSpace.EUCLIDEAN.point(x, y))
            }
            count = Integer.valueOf(reader.readLine())
            for (int ignore = 0; ignore < count; ignore++) {
                values = reader.readLine().trim().split(' ')
                x = Integer.valueOf(values[0])
                y = Integer.valueOf(values[1])
                steinerTree.newEdge(
                        steinerTree.topology.vertices.find { it.hashCode() == x },
                        steinerTree.topology.vertices.find { it.hashCode() == y }
                )
            }
        }
        new Quad<Graph, Graph, Double, Double>(
                a: graph,
                b: steinerTree,
                c: minimalSpanningTree,
                d: minimalSteinerTree
        )
    }
}
