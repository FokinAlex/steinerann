package ann

import ann.models.MstShiftModel
import api.ProjectFiles

enum ModelFacade {
    MST_SHIFT() {

        @Override
        Model load(File resource) {
            MstShiftModel model = null
            resource.withReader { reader ->
                double shift = Double.valueOf(reader.readLine())
                int inputSize = Integer.valueOf(reader.readLine())
                int hiddenSize = Integer.valueOf(reader.readLine())
                model = new MstShiftModel(resource.name, shift, inputSize, hiddenSize)
                for (int hidden = 0; hidden < hiddenSize; hidden++) {
                    model.hiddenLayer[hidden] = Double.valueOf(reader.readLine())
                }
                for (int input = 0; input < inputSize; input++) {
                    for (int hidden = 0; hidden < hiddenSize; hidden++) {
                        model.hiddenLayerWeights[input][hidden] = Double.valueOf(reader.readLine())
                    }
                }
            }
            model
        }

        @Override
        void save(Model model) {
            new File(ProjectFiles.ANN_MODELS_DIRECTORY, model.name).withPrintWriter { writer ->
                if (model instanceof MstShiftModel) {
                    // Write meta-data:
                    writer << "${ model.shift }\n"
                    writer << "${ model.hiddenLayerWeights.length }\n"
                    writer << "${ model.hiddenLayer.length }\n"
                    for (int hidden = 0; hidden < model.hiddenLayer.length; hidden++) {
                        writer << "${ model.hiddenLayer[hidden] }\n"
                    }
                    for (int input = 0; input < model.hiddenLayerWeights.length; input++) {
                        for (int hidden = 0; hidden < model.hiddenLayer.length; hidden++) {
                            writer << "${ model.hiddenLayerWeights[input][hidden] }\n"
                        }
                    }
                }
            }
        }
    };

    abstract Model load(File resource)

    abstract void save(Model model)
}
