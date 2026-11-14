import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.GregorianCalendar;

public class LegacyDateAdapter {
    public static Instant toInstant(Date date) {
        if (date == null) throw new IllegalArgumentException("date не может быть null");
        return date.toInstant();
    }

    public static Date toDate(Instant instant) {
        if (instant == null) throw new IllegalArgumentException("instant не может быть null");
        return Date.from(instant);
    }

    public static ZonedDateTime toZonedDateTime(GregorianCalendar calendar) {
        if (calendar == null) throw new IllegalArgumentException("calendar не может быть null");
        return calendar.toZonedDateTime();
    }

    public static GregorianCalendar toGregorianCalendar(ZonedDateTime zdt) {
        if (zdt == null) throw new IllegalArgumentException("zdt не может быть null");
        return GregorianCalendar.from(zdt);
    }
}