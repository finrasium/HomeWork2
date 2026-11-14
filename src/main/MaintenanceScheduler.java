import java.time.*;
import java.time.zone.ZoneRules;
import java.util.ArrayList;
import java.util.List;

public final class MaintenanceScheduler {
    private final Clock clock;

    public MaintenanceScheduler(Clock clock) {
        if (clock == null) {
            throw new IllegalArgumentException("clock не может быть null");
        }
        this.clock = clock;
    }
    public MaintenanceWindow nextWindow(
            LocalDate date, LocalTime localStart, Duration duration,
            ZoneId zone, OverlapPolicy policy) {
        if (date == null) throw new IllegalArgumentException("date не может быть null");
        if (localStart == null) throw new IllegalArgumentException("localStart не может быть null");
        if (duration == null) throw new IllegalArgumentException("duration не может быть null");
        if (zone == null) throw new IllegalArgumentException("zone не может быть null");
        if (policy == null) throw new IllegalArgumentException("policy не может быть null");
        if (duration.isNegative() || duration.isZero()){
            throw new IllegalArgumentException("duration не может быть меньше нуля");
        }
        LocalDateTime ldt = LocalDateTime.of(date, localStart);
        ZoneRules rules = zone.getRules();
        List<ZoneOffset> validOffsets = rules.getValidOffsets(ldt);
        if (validOffsets.isEmpty()) {
            throw new DateTimeException(
                    "Локальное время " + localStart + " " + date + " в зоне " + zone + " не существует (gap)");
        }
        ZonedDateTime startZdt = ZonedDateTime.of(ldt, zone);
        if (policy == OverlapPolicy.EARLIER) {
            startZdt = startZdt.withEarlierOffsetAtOverlap();
        } else {
            startZdt = startZdt.withLaterOffsetAtOverlap();
        }

        Instant start = startZdt.toInstant();
        Instant end = start.plus(duration);

        return new MaintenanceWindow(start, end);
    }
    public List<MaintenanceWindow> upcomingWindows(
            int days, LocalTime localStart, Duration duration,
            ZoneId zone, OverlapPolicy policy) {
        if (days < 0) throw new IllegalArgumentException("days не может быть отрицательным");
        if (localStart == null) throw new IllegalArgumentException("localStart не может быть null");
        if (duration == null) throw new IllegalArgumentException("duration не может быть null");
        if (zone == null) throw new IllegalArgumentException("zone не может быть null");
        if (policy == null) throw new IllegalArgumentException("policy не может быть null");
        if (days == 0) {
            return new ArrayList<>();
        }
        LocalDate startDate = LocalDate.now(clock.withZone(zone));
        List<MaintenanceWindow> result = new ArrayList<>();
        LocalDate current = startDate;
        for (int i = 0; i < days; i++) {
            result.add(nextWindow(current, localStart, duration, zone, policy));
            current = current.plusDays(1);
        }
        return result;
    }
}
