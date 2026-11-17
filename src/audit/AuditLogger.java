
package audit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditLogger {

    // Singleton instance
    private static final AuditLogger INSTANCE =
            new AuditLogger();

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private AuditLogger() {
    }

    public static AuditLogger getInstance() {
        return INSTANCE;
    }

    // Record an operation
    public void log(String message) {

        String timestamp = LocalDateTime.now()
                .format(FORMATTER);

        System.out.println(
                "[" + timestamp + "] [LOG] " + message
        );
    }
}
