package log.loggers

enum OutPrintStreamLogger {

    static call(String message) {
        println "${message}"
    }
}
