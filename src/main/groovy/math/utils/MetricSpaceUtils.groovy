package math.utils

import math.graphs.theory.Graph
import math.graphs.theory.Vertex
import math.metricspaces.EuclideanPoint
import math.metricspaces.MetricSpace
import math.metricspaces.Point

enum MetricSpaceUtils {
    EUCLIDEAN(MetricSpace.EUCLIDEAN);

    private final MetricSpace metricSpace

    private MetricSpaceUtils(MetricSpace metricSpace) {
        this.metricSpace = metricSpace
    }

    static final MetricSpaceUtils of(MetricSpace metricSpace) {
        values().find { it.metricSpace == metricSpace }
    }

    final triangleSteinerTree(Graph graph, Vertex a, Vertex b, Vertex c) {
        Point steinerPoint = of(graph.metricSpace).steinerPointFor(
                a.location,
                b.location,
                c.location
        )
        if (steinerPoint == a.location) {
            graph.newEdge(a, b)
            graph.newEdge(a, c)
        } else if (steinerPoint == b.location) {
            graph.newEdge(b, a)
            graph.newEdge(b, c)
        } else if (steinerPoint == c.location) {
            graph.newEdge(c, a)
            graph.newEdge(c, b)
        } else {
            Vertex steinerVertex = graph.newSteinerPoint(steinerPoint)
            graph.newEdge(a, steinerVertex)
            graph.newEdge(b, steinerVertex)
            graph.newEdge(c, steinerVertex)
        }
    }

    // TODO: [?] must be abstract, Euclidean only
    // TODO: [!] steinerPoint.typeProperty().setValue(STBTerminalType.STEINER_TERMINAL
    final Point steinerPointFor(Point a, Point b, Point c) {
        if (angle(b, a, c) > 120) return a
        if (angle(a, b, c) > 120) return b
        if (angle(b, c, a) > 120) return c

        Point extraPoint1 = getThirdEquilateralTrianglePoint(a, b, c)
        Point extraPoint2 = getThirdEquilateralTrianglePoint(b, c, a)

        // TODO: [!] Euclidean only
        if (extraPoint1 instanceof EuclideanPoint && extraPoint2 instanceof EuclideanPoint) {
            return getIntersection(extraPoint1, c, extraPoint2, a)
        } else {
            return null
        }
    }

    final Point getThirdEquilateralTrianglePoint(Point basis1, Point basis2, Point point) {
        Point tempPoint1 = counterclockwiseRotation(basis1, basis2, 60)
        Point tempPoint2 = counterclockwiseRotation(basis1, basis2, -60)
        return metricSpace.metric(point, tempPoint1) > metricSpace.metric(point, tempPoint2) ? tempPoint1 : tempPoint2
    }

    // TODO: [?] must be abstract, Euclidean only
    final double angle(Point point1, Point point2, Point point3) {
        double a = metricSpace.metric(point2, point3)
        double b = metricSpace.metric(point1, point3)
        double c = metricSpace.metric(point1, point2)
        return Math.toDegrees(Math.acos((a * a + c * c - b * b) / (2 * a * c)))
    }

    // TODO: [?] must be abstract, Euclidean only
    final double directionAngle(Point base, Point target) {
        if (base instanceof EuclideanPoint) {
            EuclideanPoint north = MetricSpace.EUCLIDEAN.point(base.x, Double.MIN_VALUE)
            target.x > base.x ? angle(north, base, target) : 360 - angle(north, base, target)
        }
    }

    final Point getPointByVector(Point base, double distance, double directionAngle) {
        if (base instanceof EuclideanPoint) {
            Point target = metricSpace.point(base.x, base.y - distance)
            return counterclockwiseRotation(target, base, directionAngle)
        }
        return null
    }

    // TODO: [!] must be abstract, Euclidean only
    final Point counterclockwiseRotation(Point target, Point base, double angle) {
        if (target instanceof EuclideanPoint && base instanceof EuclideanPoint) {
            return metricSpace.point(
                // x =
                    - Math.sin(Math.toRadians(angle)) * (target.y - base.y)
                    + Math.cos(Math.toRadians(angle)) * (target.x - base.x)
                    + base.x,
                // y =
                      Math.cos(Math.toRadians(angle)) * (target.y - base.y)
                    + Math.sin(Math.toRadians(angle)) * (target.x - base.x)
                    + base.y
            )
        }
        return null
    }

    // TODO: [!] must be abstract, Euclidean only
    final Point getIntersection(Point pointA1, Point pointA2, Point pointB1, Point pointB2) {
        if (pointA1 instanceof EuclideanPoint &&
            pointA2 instanceof EuclideanPoint &&
            pointB1 instanceof EuclideanPoint &&
            pointB2 instanceof EuclideanPoint) {

            double a1 = pointA1.y - pointA2.y
            double b1 = pointA2.x - pointA1.x
            double a2 = pointB1.y - pointB2.y
            double b2 = pointB2.x - pointB1.x
            double d = a1 * b2 - a2 * b1
            if (d != 0) {
                double c1 = pointA2.y * pointA1.x - pointA2.x * pointA1.y
                double c2 = pointB2.y * pointB1.x - pointB2.x * pointB1.y
                return metricSpace.point(
                    (b1 * c2 - b2 * c1) / d, // x
                    (a2 * c1 - a1 * c2) / d  // y
                )
            }
            return null
        }
    }
}
