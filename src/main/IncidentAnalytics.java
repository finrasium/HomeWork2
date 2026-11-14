import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class IncidentAnalytics {
    private static final BigInteger nanosPerSecond = BigInteger.valueOf(1_000_000_000);

    private static BigInteger durationToNanos(Duration d) {
        return BigInteger.valueOf(d.getSeconds()).multiply(nanosPerSecond).add(BigInteger.valueOf(d.getNano()));
    }
    private static Duration nanosToDuration(BigInteger nanos){
        BigInteger[] parts = nanos.divideAndRemainder(nanosPerSecond);
        long seconds = parts[0].longValueExact();
        long nanoPart = parts[1].longValueExact();
        return Duration.ofSeconds(seconds, nanoPart);
    }

    private static BigInteger sumNanos(List<Incident> incidents) {
        BigInteger total = BigInteger.ZERO;
        for (Incident incident : incidents) {
            total = total.add(durationToNanos(incident.duration()));
        }
        return total;
    }
    public static Duration totalDowntime(List<Incident> incidents) {
        if (incidents == null) {
            throw new IllegalArgumentException("incidents не могут быть null");
        }
        return nanosToDuration(sumNanos(incidents));
    }

    public static Duration averageDowntime(List<Incident> incidents){
        if (incidents == null) {
            throw new IllegalArgumentException("incidents не могут быть null");
        }
        if (incidents.isEmpty()) {
            return Duration.ZERO;
        }
        BigInteger total = sumNanos(incidents);
        BigInteger avg = total.divide(BigInteger.valueOf(incidents.size()));
        return nanosToDuration(avg);
    }
    public static Optional<Incident> longestIncident(List<Incident> incidents){
        if (incidents == null) {
            throw new IllegalArgumentException("incidents не могут быть null");
        }
        Comparator<Incident> byLongest = Comparator
                .comparing(Incident::duration)
                .thenComparing(Incident::startedAt, Comparator.reverseOrder())
                .thenComparing(Incident::id, Comparator.reverseOrder());
        return incidents.stream().max(byLongest);
    }
    public static Map<LocalDate, List<Incident>> groupByStartDate(List<Incident> incidents, ZoneId zone){
        if (incidents == null) {
            throw new IllegalArgumentException("incidents не может быть null");
        }
        if (zone == null) {
            throw new IllegalArgumentException("zone не может быть null");
        }
        List<Incident> sorted = new ArrayList<>(incidents);
        sorted.sort(Comparator
                .comparing(Incident::startedAt)
                .thenComparing(Incident::id));
        Map<LocalDate, List<Incident>> result = new TreeMap<>();
        for (Incident inc : sorted) {
            LocalDate date = inc.startedAt().atZone(zone).toLocalDate();
            result.computeIfAbsent(date, d -> new ArrayList<>()).add(inc);
        }
        return result;
    }

}
