import java.text.SimpleDateFormat;
import java.util.Date;

public class Debug {

    public static void log(String message) {
        Console.println("[" + getDateTimeString() + "] - " + message);
    }

    public static void logInfo(String message) {
        Console.println("[" + getDateTimeString() + "] - [INFO] -" + message);
    }

    public static void logWarning(String message) {
        Console.println("[" + getDateTimeString() + "] - [WARN] -" + message);
    }

    public static void logException(String message) {
        Console.println("[" + getDateTimeString() + "] - [EXCEPTION] -" + message);
    }

    public static String getDateTimeString() {
        return new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z").format(new Date(System.currentTimeMillis()));
    }
}
