package math.algorithms

@FunctionalInterface
abstract class Algorithm<Parameter, Result> extends Observable implements Runnable {

    private Result result

    abstract Algorithm(Parameter parameter)

    protected def update(AlgorithmsFeedback status) {
        setChanged()
        notifyObservers(status)
    }

    /**
     *  Returns current (sic!) algorithm's result <br>
     *  Note: it can be not final one. Check algorithms status.
     *
     *  @return algorithm's result
     *  @see AlgorithmsFeedback
     */
    def getResult() {
        result
    }
}
