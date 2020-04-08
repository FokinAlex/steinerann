package utils.orl

import api.ProjectFiles
import identification.Sequence
import math.graphs.VertexType
import math.graphs.euclidean.EuclideanGraph
import math.graphs.euclidean.EuclideanVertex
import math.metricspaces.MetricSpace
import utils.others.Duo

final class OrlCaseLoader {

    private static final Map<String, List<Duo<String, File>>> GROUPED_CASES = new LinkedHashMap<>()
    private static final Map<String, File> CASES = new HashMap<>()

    static {
        findCases(new File(ProjectFiles.ORL_CASES_DIRECTORY))
    }

    static List<Duo<String, List<String>>> getCases() {
        List<Duo<String, List<String>>> result = new LinkedList<>()
        GROUPED_CASES.each { entry ->
            result.add(
                new Duo(
                    a: entry.key,
                    b: entry.value.stream().map { it.a }.collect().toList()
                )
            )
        }
        result
    }

    static void findCases(File casesDirectory) {
        if (casesDirectory.isDirectory()) {
            Map<String, List<Duo<String, File>>> cases = new LinkedHashMap<>()
            // TODO: sort
            casesDirectory.eachFile { groupDirectory ->
                List<Duo<String, File>> group = new LinkedList<>()
                groupDirectory.eachFile {
                    group.add(new Duo(a: it.getName(), b: it))
                    CASES.put(it.getName(), it)
                }
                GROUPED_CASES.put(groupDirectory.getName(), group)
            }
        }
    }

    static EuclideanGraph loadCase(String caseName) {
        File resource = CASES.get(caseName)

        EuclideanGraph graph = new EuclideanGraph()
        EuclideanVertex vertex

        int countOfPoint
        String[] location
        int id
        double x
        double y
        resource.withReader { reader ->
            // TODO: Save it
            reader.readLine() // save Smt
            reader.readLine() // save Mst
            countOfPoint = Integer.valueOf(reader.readLine())
            for (int j = 0; j < countOfPoint; j++) {
                location = reader.readLine().split(' ')
                x = Double.valueOf(location[1])
                y = Double.valueOf(location[2])
                vertex = new EuclideanVertex(graph.getVertexSequnce(), MetricSpace.EUCLIDEAN.point(x, y))
                graph.addVertex(vertex)
            }
            countOfPoint = Integer.valueOf(reader.readLine())
            for (int j = 0; j < countOfPoint; j++) {
                location = reader.readLine().trim().split(' ')
                x = Double.valueOf(location[1])
                y = Double.valueOf(location[2])
                vertex = new EuclideanVertex(graph.getVertexSequnce(), MetricSpace.EUCLIDEAN.point(x, y))
                vertex.setType(VertexType.STEINER)
                graph.addVertex(vertex)
            }
        }
        graph
    }
}
