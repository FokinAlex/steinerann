package ann

import math.graphs.theory.Graph

interface Model {

    String getName()

    void calculate(Graph graph)
}