package utils.orl

import api.ProjectFiles
import math.graphs.VertexTypes
import math.graphs.theory.Graph
import math.graphs.theory.Vertex
import math.metricspaces.MetricSpace
import utils.others.Duo

final class OrlCaseLoader {

    private static final Map<Integer, List<Duo<String, File>>> GROUPED_CASES = new LinkedHashMap<>()
    private static final Map<String, File> CASES = new HashMap<>()

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

    static Graph loadCase(String caseName) {
        File resource = CASES.get(caseName)

        Graph graph = new Graph(MetricSpace.EUCLIDEAN)
        Vertex vertex

        int countOfPoint
        String[] location
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
                graph.newVertex(MetricSpace.EUCLIDEAN.point(x, y))
            }
            countOfPoint = Integer.valueOf(reader.readLine())
            for (int j = 0; j < countOfPoint; j++) {
                location = reader.readLine().trim().split(' ')
                x = Double.valueOf(location[1])
                y = Double.valueOf(location[2])
                vertex = graph.newVertex(MetricSpace.EUCLIDEAN.point(x, y))
                vertex.setType(VertexTypes.STEINER)
            }
        }
        graph
    }
}
