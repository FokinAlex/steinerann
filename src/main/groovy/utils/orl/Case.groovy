package utils.orl

import identification.Identifiable
import identification.Sequence

final class Case {

    /**
     * Steiner minimum tree
     */
    double smt

    /**
     * Minimum spanning tree
     */
    double mst

    final Set<Point> points = new HashSet<>()
    final Set<Point> steinerPoints = new HashSet<>()

    boolean addPoint(double x, double y) {
        points.add(new Point(x, y, sequence))
    }

    boolean addSteinerPoint(double x, double y) {
        steinerPoints.add(new Point(x, y, sequence))
    }

    private Sequence sequence = new Sequence()

    @Override
    String toString() {
        String result = "${points.size()}\n"
        points.each {
            result += "${it}\n"
        }
        result
    }

    void writeToFile(File file) {
        file.withPrintWriter { writer ->
            writer << "${this.smt}\n"
            writer << "${this.mst}\n"
            writer << "${this.points.size()}\n"
            this.points.each {
                writer << "${it}\n"
            }
            writer << "${this.steinerPoints.size()}\n"
            this.steinerPoints.each {
                writer << "${it}\n"
            }
        }
    }

    class Point extends Identifiable {

        final double x
        final double y

        protected Point(double x, double y, Sequence sequence) {
            super(sequence)
            this.x = x
            this.y = y
        }

        @Override
        boolean equals(Object obj) {
            if (obj instanceof Point && this.x == obj.x && this.y == obj.x) return true
            super.equals(obj)
        }

        @Override
        String toString() {
            "${id} ${x} ${y}"
        }
    }
}