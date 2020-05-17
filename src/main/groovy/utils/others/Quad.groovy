package utils.others

final class Quad<A, B, C, D> {

    A a
    B b
    C c
    D d

    @Override
    final String toString() {
        "[${a}, ${b}, ${c}, ${d}]"
    }
}
