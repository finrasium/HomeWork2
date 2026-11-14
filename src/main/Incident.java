import java.time.Duration;
import java.time.Instant;

public record Incident(
        String id,
        String service,
        Instant startedAt,
        Instant resolvedAt) {
    public Incident {
        if (id == null || id.isBlank()){
            throw new IllegalArgumentException("ID не может быть пустым или null");
        }
        if (service == null || service.isBlank()) {
            throw new IllegalArgumentException("service не может быть пустым или null");
        }
        if (startedAt == null){
            throw new NullPointerException("startedAt не должен быть null");
        }
        if (resolvedAt == null){
            throw new NullPointerException("resolvedAt не должен быть null");
        }
        if (resolvedAt.isBefore(startedAt)) {
            throw new IllegalArgumentException("resolvedAt не должен быть раньше startedAt: " + startedAt + " > " + resolvedAt);
        }
    }
    public Duration duration() {
        return Duration.between(startedAt, resolvedAt);
    }
}
