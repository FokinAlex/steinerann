package math.algorithms

// TODO: ObservableValue<Argument> ?
interface Algorithm<Argument> extends Runnable {

    def step()

    def completed()
}