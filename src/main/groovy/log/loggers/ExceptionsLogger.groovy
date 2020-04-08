package log.loggers

enum ExceptionsLogger {

    private final static JOURNAL = new File("logs", "exceptions.log")

    static call(String message) {
        // TODO: add rewriting
        JOURNAL << "${message}\n"
    }
}
