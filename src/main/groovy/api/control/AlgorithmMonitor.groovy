package api.control

import api.enities.pages.AlgorithmFriendlyPage
import log.LogFacade
import math.algorithms.Algorithm
import math.graphs.theory.Graph

final enum AlgorithmMonitor {
    INSTANCE;

    private final Map<String, Set<AlgorithmType>> INNER_ALGORITHMS = Map.of(
            "Задача Штейнера", new HashSet<>(SteinerAlgorithms.values().collect()) as Set<AlgorithmType>,
            "Другие", new HashSet<>(OtherGraphAlgorithms.values().collect()) as Set<AlgorithmType>
    )

    final Map<String, List<String>> ALGORITHMS = Map.of(
            "Задача Штейнера", SteinerAlgorithms.values().collect { it.name } as List<String>,
            "Другие", OtherGraphAlgorithms.values().collect { it.name } as List<String>
    )

    // private final Map<Algorithm, Duo<Thread, AlgorithmFriendlyPage>> MONITOR = new HashMap<>()

    // TODO: return Observable
    def runAlgorithm(String group, String name, AlgorithmFriendlyPage<Graph> page) {
        Algorithm algorithm = INNER_ALGORITHMS.get(group).find { it.name == name }.initialize(page.getArgument())
        LogFacade.INFO("Algorithm ${algorithm} initialized with ${page.getArgument()}")

        Thread thread = new Thread(algorithm)
        // MONITOR.put(algorithm, new Duo(a: thread, b: page))
        // TODO: add algorithm listener
        // -> page.update()
        LogFacade.INFO("Thread ${thread} initialized")

        thread.start()
        LogFacade.INFO("Thread ${thread} started")

        // TODO: temp replace
        thread.join()
        page.update()
    }
}
