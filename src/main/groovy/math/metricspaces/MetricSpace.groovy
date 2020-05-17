package math.metricspaces

import math.utils.MathUtils

enum MetricSpace {
    EUCLIDEAN ({ first, second ->
        if (first instanceof EuclideanPoint && second instanceof EuclideanPoint) {
            Math.sqrt(MathUtils.square(first.x.value - second.x.value) + MathUtils.square(first.y.value - second.y.value))
        } else {
            throw new IllegalArgumentException("Not euclidean point in Euclidean space")
        }
    }) {

        @Override
        <Param> EuclideanPoint point(Param... params) {
            if (params.length != COUNT_OF_EUCLIDEAN_POINT_PARAMS) {
                // TODO:
                throw new IllegalArgumentException("It must be pair (x, y) to initialize point in Euclidean space")
            }
            if (!(params[0] instanceof Double && params[1] instanceof Double)) {
                // TODO:
                throw new IllegalArgumentException("Values of pair (x, y) must be instance of double")
            }
            new EuclideanPoint(params[0] as Double, params[1] as Double)
        }
    };

    private static final int COUNT_OF_EUCLIDEAN_POINT_PARAMS = 2

    final Closure metric

    MetricSpace(Closure metric) {
        this.metric = metric
    }

    abstract <Param> Point point(Param... params)
}
