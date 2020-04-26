package utils.others

final class Duo<A, B> {

    A a
    B b

    @Override
    final String toString() {
        "[${a}, ${b}]"
    }
}
