package utils.others

final class Triple<A, B, C> {

    A a
    B b
    C c

    @Override
    final String toString() {
        "[${a}, ${b}, ${c}]"
    }
}
