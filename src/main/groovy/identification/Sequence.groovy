package identification

final class Sequence {

    protected int current

    Sequence() {
        this(0)
    }

    Sequence(int start) {
        current = start
    }

    int next() throws Exception {
        if (Integer.MAX_VALUE == current)
            throw new Exception("Too many values")
        current++
    }
}
