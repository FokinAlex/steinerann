package api.control

interface AlgorithmType<T> {

    String getName()

    void run(T t)
}