package utils.logs;

public class Logger {

    public static void log(String message, LogLevel logLevel) {
        System.out.println("[" + logLevel + "]" + message);
    }

    public static void logDebug(String message) {
        log(message, LogLevel.DEBUG);
    }

    public static void logInfo(String message) {
        log(message, LogLevel.INFO);
    }

    public static void logWarn(String message) {
        log(message, LogLevel.WARN);
    }
    public static void logError(String message) {
        log(message, LogLevel.ERROR);
    }
}
