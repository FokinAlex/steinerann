package api.control

import math.algorithms.Algorithm

interface AlgorithmType<Input> {

    String getName()

    Algorithm initialize(Input input)
}