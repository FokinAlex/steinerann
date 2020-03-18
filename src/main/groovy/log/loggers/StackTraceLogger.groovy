package log.loggers

enum StackTraceLogger {

    private final static LAST_STACK_TRACE = new File("logs", "last_stack_trace.log")

    static call(Exception exception) {
        LAST_STACK_TRACE.withPrintWriter { writer ->
            exception.printStackTrace(writer)
        }
    }
}
