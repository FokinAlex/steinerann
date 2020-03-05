package math.metricspaces

import math.utils.MathUtils

enum MetricSpace {

    EUCLIDEAN (
            metric: { first, second ->
                if (first instanceof EuclideanPoint && second instanceof EuclideanPoint) {
                    // TODO: get root
                    MathUtils.square(first.x - second.x) + MathUtils.square(first.y - second.y)
                } else {
                    throw new IllegalArgumentException("Not euclidean point in Euclidean space")
                }
            }
    ) {

        /**
         * TODO:
         *
         * @param params
         * @return
         */
        @Override
        <Param> Point point(Param... params) {
            if (params.length != COUNT_OF_EUCLIDEAN_POINT_PARAMS) {
                // TODO:
                throw new IllegalArgumentException("It must be pair (x, y) to initialize point in Euclidean space")
            }
            if (!(params[0] instanceof Double) && !(params[1] instanceof Double)) {
                // TODO:
                throw new IllegalArgumentException("Values of pair (x, y) must be instance of Double")
            }
            new EuclideanPoint(x: (Double) params[0], y: (Double) params[1])
        }
    };

    private static final int COUNT_OF_EUCLIDEAN_POINT_PARAMS = 2

    Closure metric

    MetricSpace(Closure metric) {
        this.metric = metric
    }

    abstract <Param> Point point(Param... params)

    interface Point { }

    private class EuclideanPoint implements Point {

        private double x
        private double y
    }
}
