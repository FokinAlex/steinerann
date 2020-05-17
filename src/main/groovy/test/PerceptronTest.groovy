package test

import ann.AnnTeacher
import ann.models.DistanceAnglePerceptronModel
import math.graphs.theory.Graph
import utils.orl.OrlCaseLoader

//DistanceAnglePerceptronModel model = DistanceAnglePerceptronModel.load("perceptron-test1")

//AnnTeacher.trainWithOrl(model, 0.25)

//double accuracy = AnnTeacher.testAccuracy(model, "3-1.orl")
//println accuracy

// Random
//double[] distances = new double[100]
//double[] angles   = new double[100]
//for (int i = 0; i < 100; i++) {
//    distances[i] = Math.random() * 1.5
//    angles[i]    = Math.random() * 360
//}
//model.run(distances, angles)


Graph g = OrlCaseLoader.loadCase("3-1.orl").b
println g.getWeight()



//println accuracy

//accuracy = AnnTeacher.testAccuracy(model, "3-5.orl")
//println accuracy


// PerceptronModel.save(model)
