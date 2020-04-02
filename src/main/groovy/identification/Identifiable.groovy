package identification

class Identifiable {

    protected final int id

    protected Identifiable(Sequence sequence) throws Exception {
        this.id = sequence.next()
    }

    @Override
    boolean equals(Object obj) {
        obj instanceof Identifiable && this.id == obj.id
    }

    @Override
    final int hashCode() {
        this.id
    }
}

