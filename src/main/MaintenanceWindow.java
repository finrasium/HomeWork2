import java.time.Duration;
import java.time.Instant;

public record MaintenanceWindow(Instant start, Instant end ) {
    public MaintenanceWindow {
        if (start == null) {
            throw new IllegalArgumentException("start не может быть null");
        }
        if (end == null) {
            throw new IllegalArgumentException("end не может быть null");
        }
        if (end.isBefore(start)) {
            throw new IllegalArgumentException("end не может быть раньше start");
        }
    }

    public Duration duration() {
        return Duration.between(start, end);
    }
}
