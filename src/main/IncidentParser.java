import java.time.DateTimeException;
import java.time.Instant;

public final class IncidentParser {
    public Incident parse(String line){
        if (line == null) {
            throw new IllegalArgumentException("line не может быть null");
        }
        String[] parts = line.split(";", -1);
        if (parts.length != 4) {
            throw new IllegalArgumentException(
                    "нам нужно 4 поля, а получили " + parts.length + ": " + line);
        }
        String id = parts[0];
        String service = parts[1];
        String startedText = parts[2];
        String resolvedText = parts[3];

        if (id.isBlank()){
            throw new IllegalArgumentException("ID не может быть пустым");
        }
        if (service.isBlank()) {
            throw new IllegalArgumentException("service не может быть пустым");
        }
        Instant startedAt = parseInstant(startedText, "startedAt", line);
        Instant resolvedAt = parseInstant(resolvedText, "resolvedAt", line);
        return new Incident(id,service,startedAt,resolvedAt);
    }
    private static Instant parseInstant(String text, String field, String line) {
        if (text.isBlank()) {
            throw new IllegalArgumentException(field + " не должно быть пустым: " + line);
        }
        try {
            return Instant.parse(text);
        } catch(DateTimeException e){
            throw new IllegalArgumentException(
                    field + " невалидный Instant: '" + text + "' в: " + line, e);
        }
    }
}
