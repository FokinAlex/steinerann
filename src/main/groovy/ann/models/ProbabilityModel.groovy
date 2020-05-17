package ann.models

import api.ProjectFiles

class ProbabilityModel {

    static final int OUTPUT_LAYER_SIZE = 10_000

    final String name

    final double[] inputLayer
    final double[] hiddenLayer
    final double[] outputLayer

    final double[][] inputHiddenWeights
    final double[][] hiddenOutputWeights

    int iterations
    long totalLearningTime

    private ProbabilityModel(String name,
                             int inputLayerSize,
                             int hiddenLayerSize) {
        this.name = name
        // Layers:
        inputLayer  = new double[inputLayerSize]
        hiddenLayer = new double[hiddenLayerSize]
        outputLayer = new double[OUTPUT_LAYER_SIZE]
        // Weights:
        inputDistanceHiddenWeights = new double[inputLayerSize][hiddenLayerSize]
        inputAngleHiddenWeights    = new double[inputLayerSize][hiddenLayerSize]
        hiddenOutputWeights        = new double[hiddenLayerSize][OUTPUT_LAYER_SIZE]
    }

    static DistanceAnglePerceptronModel generate(String name, int inputLayerSize, int hiddenLayerSize) {
        DistanceAnglePerceptronModel model = new ProbabilityModel(name, inputLayerSize, hiddenLayerSize)
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
}
