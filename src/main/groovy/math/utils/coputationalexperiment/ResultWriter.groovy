package math.utils.coputationalexperiment

import api.ProjectFiles

final class ResultWriter {

    private final File resultFile

    private ResultWriter(String resultName) {
        this.resultFile = new File(
                ProjectFiles.RESULTS_DIRECTORY,
                "${(System.currentTimeMillis() / 1000 as long) % 10_000_000}-${resultName}"
        )
        resultFile.withWriter { it << "" }
    }

    static final ResultWriter instance(String resultName) {
        new ResultWriter(resultName)
    }

    def writeLine(String... values) {
        resultFile.withWriterAppend { writer ->
            writer << "${values.join('\t')}\n"
        }
    }

    def leftShift(String line) {
        resultFile.withWriterAppend { writer ->
            writer << "${line}\t"
        }
    }

    def endLine() {
        resultFile.withWriterAppend { writer ->
            writer << "\n"
        }
    }
}
