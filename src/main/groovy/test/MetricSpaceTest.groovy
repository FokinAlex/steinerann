package test

import log.LogFacade
import math.metricspaces.EuclideanPoint
import math.metricspaces.MetricSpace
import math.utils.MetricSpaceUtils

EuclideanPoint a
EuclideanPoint b
EuclideanPoint c
EuclideanPoint d
double distance
double angle

LogFacade.INFO("Test 1: 90 degrees")
a = MetricSpace.EUCLIDEAN.point(0d, 0d) as EuclideanPoint
b = MetricSpace.EUCLIDEAN.point(0d, 1d) as EuclideanPoint
c = MetricSpace.EUCLIDEAN.point(1d, 1d) as EuclideanPoint
angle = MetricSpaceUtils.EUCLIDEAN.angle(a, b, c)
LogFacade.INFO("Result: ${angle}")
angle = MetricSpaceUtils.EUCLIDEAN.directionAngle(b, c)
LogFacade.INFO("Result: ${angle}")

LogFacade.INFO("Test 2: 225 degrees")
a = MetricSpace.EUCLIDEAN.point(0d, 0d) as EuclideanPoint
b = MetricSpace.EUCLIDEAN.point(0d, 1d) as EuclideanPoint
c = MetricSpace.EUCLIDEAN.point(-1d, 2d) as EuclideanPoint
angle = MetricSpaceUtils.EUCLIDEAN.angle(a, b, c)
LogFacade.INFO("Result: ${angle}")
angle = MetricSpaceUtils.EUCLIDEAN.directionAngle(b, c)
LogFacade.INFO("Result: ${angle}")

LogFacade.INFO("Test 3: from vector")
a = MetricSpace.EUCLIDEAN.point(0d, 0d) as EuclideanPoint
distance = 1
angle    = 90
LogFacade.INFO("Result: ${MetricSpaceUtils.EUCLIDEAN.getPointByVector(a, distance, angle)}")

LogFacade.INFO("Test 4: from vector")
a = MetricSpace.EUCLIDEAN.point(0d, 0d) as EuclideanPoint
distance = 1.414
angle    = 225
LogFacade.INFO("Result: ${MetricSpaceUtils.EUCLIDEAN.getPointByVector(a, distance, angle)}")

LogFacade.INFO("Test 5: Useless test")
a = MetricSpace.EUCLIDEAN.point(0.13d, 0.53d) as EuclideanPoint
b = MetricSpace.EUCLIDEAN.point(0.40d, 0.87d) as EuclideanPoint
c = MetricSpace.EUCLIDEAN.point(0.53d, 0.40d) as EuclideanPoint
d = MetricSpace.EUCLIDEAN.point(0.67d, 0.67d) as EuclideanPoint
distance = MetricSpace.EUCLIDEAN.metric(a, b)
angle = MetricSpaceUtils.EUCLIDEAN.directionAngle(a, b)
LogFacade.INFO("a - b distance: ${distance}\tangle: ${angle}")
distance = MetricSpace.EUCLIDEAN.metric(a, c)
angle = MetricSpaceUtils.EUCLIDEAN.directionAngle(a, c)
LogFacade.INFO("a - c distance: ${distance}\tangle: ${angle}")
distance = MetricSpace.EUCLIDEAN.metric(a, d)
angle = MetricSpaceUtils.EUCLIDEAN.directionAngle(a, d)
LogFacade.INFO("a - d distance: ${distance}\tangle: ${angle}")
distance = MetricSpace.EUCLIDEAN.metric(b, a)
angle = MetricSpaceUtils.EUCLIDEAN.directionAngle(b, a)
LogFacade.INFO("b - a distance: ${distance}\tangle: ${angle}")
distance = MetricSpace.EUCLIDEAN.metric(b, c)
angle = MetricSpaceUtils.EUCLIDEAN.directionAngle(b, c)
LogFacade.INFO("b - c distance: ${distance}\tangle: ${angle}")
distance = MetricSpace.EUCLIDEAN.metric(b, d)
angle = MetricSpaceUtils.EUCLIDEAN.directionAngle(b, d)
LogFacade.INFO("b - d distance: ${distance}\tangle: ${angle}")
distance = MetricSpace.EUCLIDEAN.metric(c, a)
angle = MetricSpaceUtils.EUCLIDEAN.directionAngle(c, a)
LogFacade.INFO("c - a distance: ${distance}\tangle: ${angle}")
distance = MetricSpace.EUCLIDEAN.metric(c, b)
angle = MetricSpaceUtils.EUCLIDEAN.directionAngle(c, b)
LogFacade.INFO("c - b distance: ${distance}\tangle: ${angle}")
distance = MetricSpace.EUCLIDEAN.metric(c, d)
angle = MetricSpaceUtils.EUCLIDEAN.directionAngle(c, d)
LogFacade.INFO("c - d distance: ${distance}\tangle: ${angle}")
distance = MetricSpace.EUCLIDEAN.metric(d, a)
angle = MetricSpaceUtils.EUCLIDEAN.directionAngle(d, a)
LogFacade.INFO("d - a distance: ${distance}\tangle: ${angle}")
distance = MetricSpace.EUCLIDEAN.metric(d, b)
angle = MetricSpaceUtils.EUCLIDEAN.directionAngle(d, b)
LogFacade.INFO("d - b distance: ${distance}\tangle: ${angle}")
distance = MetricSpace.EUCLIDEAN.metric(d, c)
angle = MetricSpaceUtils.EUCLIDEAN.directionAngle(d, c)
LogFacade.INFO("d - c distance: ${distance}\tangle: ${angle}")

/*



a - b | d: 0.434165
      | a: 141.54

a - c | d: 0.420594
      | a: 71.995

a - d | d: 0.557853
      | a: 104.53

b - a | d: 0.434165
      | a: 321.54

b - c | d: 0.487647
      | a: 15.461

b - d | d: 0.336005
      | a: 53.471

c - a | d: 0.420594
      | a: 251.99

c - b | d: 0.487647
      | a: 195.46

c - d | d: 0.304138
      | a: 152.59

d - a | d: 0.557853
      | a: 284.53

d - b | d: 0.336005
      | a: 233.47

d - c | d: 0.304138
      | a: 332.59


 */

