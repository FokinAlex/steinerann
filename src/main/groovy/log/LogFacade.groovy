package log

import log.loggers.ExceptionsLogger
import log.loggers.OutPrintStreamLogger
import log.loggers.StackTraceLogger

enum LogFacade {
    INFO(INFO_PREFIX),
    WARN(WARN_PREFIX),
    ERROR(ERROR_PREFIX);
    
    private final static String INFO_PREFIX  = "INFO  "
    private final static String WARN_PREFIX  = "WARN  "
    private final static String ERROR_PREFIX = "ERROR "

    private  final String prefix
    
    LogFacade(String prefix) {
        this.prefix = prefix
    }
    
    def call(String message) {
        OutPrintStreamLogger.call(message)
    }
    
    def call(String message, Exception exception) {
        OutPrintStreamLogger.call(message)
        ExceptionsLogger.call(message)
        StackTraceLogger.call(exception)
    }
}
