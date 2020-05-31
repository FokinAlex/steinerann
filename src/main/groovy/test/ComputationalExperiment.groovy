package test

import api.control.SteinerAlgorithms
import math.algorithms.Algorithm
import math.utils.coputationalexperiment.ResultWriter
import utils.orl.OrlCaseLoader

//String experiment = "smith-lee-leibman"
//String algorithmName = "Алгоритм Смита-Лии-Лейбмана"
//String experiment = "greedy-classic"
//String algorithmName = "Алгоритм жадный классический"
String experiment = "mst-20"
String algorithmName = "Сдвиговый алгоритм (20)"

println "Start computational Experiment '${experiment}'"

ResultWriter writer = ResultWriter.instance(experiment)
String[] meta = [ "Алгоритм", "Задание", "Множество N", "Множество S", "Время (мс)", "Резоеультат", "Мин. остовное", "Мин. Штейнера" ]
writer.writeLine(meta)


double _steinerShift = 0    // ?
double _spanningShift = 0   // ?

OrlCaseLoader.CASES.keySet().each { caseName ->
    def orlTask = OrlCaseLoader.loadTask(caseName)
    Algorithm algorithm = SteinerAlgorithms.MST_SHIFT_20.initialize(orlTask.a)
    println "Start with ${caseName}"
    long millis = System.nanoTime()
    algorithm.run()
    millis = System.nanoTime() - millis

    String task = caseName
    String nPoints = orlTask.a.topology.vertices.size() - orlTask.a.steinerPoints.size()
    String sPoints = orlTask.a.steinerPoints.size()
    String time = millis
    String result = orlTask.a.weight
    String minimalSteinerTree = orlTask.c
    String minimalSpanningTree = orlTask.d
    String[] data = [ algorithmName, task, nPoints, sPoints, time, result, minimalSpanningTree, minimalSteinerTree ]
    writer.writeLine(data)
}

println "- - - - - - - - - - Stop - - - - - - - - - -"
