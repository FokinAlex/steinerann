package math.algorithms

@FunctionalInterface
abstract class Algorithm<Parameter, Result> extends Observable implements Runnable {

    private Parameter parameter
    private Result result
    AlgorithmsFeedback status

    abstract Algorithm(Parameter parameter)

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
