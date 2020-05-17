package math.metricspaces

class EuclideanPoint implements Point {

    double x
    double y

    EuclideanPoint(Double x, Double y) {
        this.x = x
        this.y = y
    }

    @Override
    String toString() {
        "x: ${x}, y: ${y}"
    }
}

