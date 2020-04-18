package math.metricspaces

import javafx.beans.property.DoubleProperty
import javafx.beans.property.SimpleDoubleProperty

class EuclideanPoint implements Point {

    DoubleProperty x
    DoubleProperty y

    EuclideanPoint(Double x, Double y) {
        this.x = new SimpleDoubleProperty(x)
        this.y = new SimpleDoubleProperty(y)
    }
}

