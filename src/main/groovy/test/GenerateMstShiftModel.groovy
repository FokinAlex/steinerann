package test

import ann.ModelFacade
import ann.models.MstShiftModel

println "Started ..."

MstShiftModel model = new MstShiftModel(
    "shift-model-05201",
    15d,
    100,
    20
)

println "Filling ..."

for (int hidden = 0; hidden < model.hiddenLayer.length; hidden++) {
    model.hiddenLayer[hidden] = Math.random()
}
for (int input = 0; input < model.hiddenLayerWeights.length; input++) {
    for (int hidden = 0; hidden < model.hiddenLayer.length; hidden++) {
        model.hiddenLayerWeights[input][hidden] = Math.random()
    }
}

println "Filled"

ModelFacade.MST_SHIFT.save(model)

println "Saved"
