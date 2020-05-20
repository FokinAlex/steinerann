package trash

import api.ProjectFiles
import log.LogFacade
import utils.others.Duo

class DistanceAnglePerceptronModel {

    static final int OUTPUT_LAYER_SIZE    = 2
    static final int OUTPUT_DISTANCE_NODE = 0
    static final int OUTPUT_ANGLE_NODE    = 1

    static final double DISTANCE_RANGE_MULTIPLIER = 1.5
    static final double ANGLE_RANGE_MULTIPLIER    = 360

    final String name

    final double[] inputDistancesLayer
    final double[] inputAnglesLayer
    final double[] hiddenLayer
    final double[] outputLayer

    final double[][] inputDistanceHiddenWeights
    final double[][] inputAngleHiddenWeights
    final double[][] hiddenOutputWeights

    int iterations
    long totalLearningTime

    private DistanceAnglePerceptronModel(String name,
                                         int inputLayerSize,
                                         int hiddenLayerSize) {
        this.name = name
        // Layers:
        inputDistancesLayer = new double[inputLayerSize]
        inputAnglesLayer    = new double[inputLayerSize]
        hiddenLayer         = new double[hiddenLayerSize]
        outputLayer         = new double[OUTPUT_LAYER_SIZE]
        // Weights:
        inputDistanceHiddenWeights = new double[inputLayerSize][hiddenLayerSize]
        inputAngleHiddenWeights    = new double[inputLayerSize][hiddenLayerSize]
        hiddenOutputWeights        = new double[hiddenLayerSize][OUTPUT_LAYER_SIZE]
    }

    static DistanceAnglePerceptronModel generate(String name, int inputLayerSize, int hiddenLayerSize) {
        DistanceAnglePerceptronModel model = new DistanceAnglePerceptronModel(name, inputLayerSize, hiddenLayerSize)
        model.iterations        = 0
        model.totalLearningTime = 0

        for (int i = 0; i < model.inputDistancesLayer.size(); i++) {
            for (int j = 0; j < model.hiddenLayer.size(); j++) {
                model.inputDistanceHiddenWeights[i][j] = Math.random()
                model.inputAngleHiddenWeights   [i][j] = Math.random()
            }
        }
        for (int i = 0; i < model.hiddenLayer.size(); i++) {
            for (int j = 0; j < OUTPUT_LAYER_SIZE; j++) {
                model.hiddenOutputWeights[i][j] = Math.random()
            }
        }
        model
    }

    static final save(DistanceAnglePerceptronModel model) {
        new File(ProjectFiles.ANN_PERCEPTRON_DIRECTORY, model.name).withPrintWriter { writer ->
            // Write meta-data:
            writer << "${ model.iterations                 }\n"
            writer << "${ model.totalLearningTime          }\n"
            writer << "${ model.inputDistancesLayer.size() }\n"
            writer << "${ model.hiddenLayer.size()         }\n"

            for (int i = 0; i < model.inputDistancesLayer.size(); i++) {
                for (int j = 0; j < model.hiddenLayer.size(); j++) {
                    writer << "${ model.inputDistanceHiddenWeights[i][j] }\n"
                    writer << "${ model.inputAngleHiddenWeights   [i][j] }\n"
                }
            }
            for (int i = 0; i < model.hiddenLayer.size(); i++) {
                for (int j = 0; j < OUTPUT_LAYER_SIZE; j++) {
                    writer << "${ model.hiddenOutputWeights[i][j] }\n"
                }
            }
        }
    }

    static DistanceAnglePerceptronModel load(String name) {
        DistanceAnglePerceptronModel model = null
        int iterations
        long totalLearningTime
        int inputLayerSize
        int hiddenLayerSize

        new File(ProjectFiles.ANN_PERCEPTRON_DIRECTORY, name).withReader { reader ->
            // Read meta-data:
            iterations        = Integer.valueOf(reader.readLine())
            totalLearningTime =    Long.valueOf(reader.readLine())
            inputLayerSize    = Integer.valueOf(reader.readLine())
            hiddenLayerSize   = Integer.valueOf(reader.readLine())

            // Model:
            model = new DistanceAnglePerceptronModel(name, inputLayerSize, hiddenLayerSize)
            model.iterations        = iterations
            model.totalLearningTime = totalLearningTime

            for (int i = 0; i < inputLayerSize; i++) {
                for (int j = 0; j < hiddenLayerSize; j++) {
                    model.inputDistanceHiddenWeights[i][j] = Double.valueOf(reader.readLine())
                    model.inputAngleHiddenWeights   [i][j] = Double.valueOf(reader.readLine())
                }
            }
            for (int i = 0; i < hiddenLayerSize; i++) {
                for (int j = 0; j < OUTPUT_LAYER_SIZE; j++) {
                    model.hiddenOutputWeights[i][j] = Double.valueOf(reader.readLine())
                }
            }
        }
        model
    }

    /**
     * @param distances in range [0 - 1.5]
     * @param angles in range [0 - 360]
     *
     * @return pair [distance, angle]
     */
    Duo<Double, Double> run(double[] distances, double[] angles) {
        LogFacade.INFO("Perceptron Model called")
        LogFacade.INFO("Distances: ${distances}")
        for (int i = 0; i < distances.size(); i++) distances[i] /= DISTANCE_RANGE_MULTIPLIER
        LogFacade.INFO("mapped to: ${distances}")
        LogFacade.INFO("Angles:    ${angles}")
        for (int i = 0; i < angles.size(); i++) angles[i] /= ANGLE_RANGE_MULTIPLIER
        LogFacade.INFO("mapped to: ${angles}")

        if (distances.size() <= inputDistancesLayer.size() && distances.size() == angles.size()) {
            double alpha
            double beta

            for (int i = 0; i < distances.size(); i++) {
                inputDistancesLayer[i] = distances[i]
                inputAnglesLayer[i]    = angles[i]
            }
            for (int i = distances.size(); i < inputDistancesLayer.size(); i++) {
                inputDistancesLayer[i] = 0
                inputAnglesLayer[i]    = 0
            }

            alpha = (inputDistancesLayer.size() + inputAnglesLayer.size()) * 0.25
            beta  = 10 / alpha
            LogFacade.INFO("Input -> Hidden: alpha = ${alpha}, beta = ${beta}")
            for (int hidden = 0; hidden < hiddenLayer.size(); hidden++) {
                hiddenLayer[hidden] = 0
                for (int input = 0; input < inputDistancesLayer.size(); input++) {
                    hiddenLayer[hidden] += inputDistancesLayer[input] * inputDistanceHiddenWeights[input][hidden]
                    hiddenLayer[hidden] += inputAnglesLayer[input]    * inputAngleHiddenWeights[input][hidden]
                }
                hiddenLayer[hidden] = 1 / (1 + Math.exp(- (hiddenLayer[hidden] - alpha) * beta))
                LogFacade.INFO("Hidden [${hidden}] \t${hiddenLayer[hidden]}")
            }

            alpha = hiddenLayer.size() * 0.25
            beta  = 10 / alpha
            LogFacade.INFO("Hidden -> Output: alpha = ${alpha}, beta = ${beta}")
            for (int output = 0; output < outputLayer.size(); output++) {
                outputLayer[output] = 0
                for (int hidden = 0; hidden < hiddenLayer.size(); hidden++) {
                    outputLayer[output] += hiddenLayer[hidden] * hiddenOutputWeights[hidden][output]
                }
                outputLayer[output] = 1 / (1 + Math.exp(- (outputLayer[output] - alpha) * beta))
                LogFacade.INFO("Output [${output}] \t${outputLayer[output]}")
            }
            LogFacade.INFO("Perceptron Model calculations completed")
            return new Duo<Double, Double>(a: outputLayer[OUTPUT_DISTANCE_NODE], b: outputLayer[OUTPUT_ANGLE_NODE])
        }
        LogFacade.ERROR(new IllegalArgumentException("Something is going wrong with input data"))
        return null
    }

    def learn() {
        double[] hiddenLayerMistakes = new double[hiddenLayer.size()]
        double[] outputLayerMistakes = new double[outputLayer.size()]
        double   totalMistake
        double   stepSize
//        do {
//            totalMistake = 0
//            double [][][] learningStaff
//            double [][]   learningSet = new double[learningStaff.length][]
//            double [][]   answersSet = new double[learningStaff.length][]
//            for (int i = 0; i < learningStaff.length; i++) {
//                learningSet[i] = learningStaff[i][1]
//                answersSet[i]  = learningStaff[i][0]
//            }
//            // TODO: ^ do it right
////            for () {
////
////            }
//
//
//        } while (true)
    }
}
