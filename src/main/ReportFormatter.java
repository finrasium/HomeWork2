import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ReportFormatter {
    public static String formatWindow(MaintenanceWindow window, ZoneId zone, Locale locale) {
        if (window == null) throw new IllegalArgumentException("window не может быть null");
        if (zone == null) throw new IllegalArgumentException("zone не может быть null");
        if (locale == null) throw new IllegalArgumentException("locale не может быть null");

        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("dd.MM.uuuu HH:mm XXX (VV)", locale)
                .withZone(zone);

        String startStr = formatter.format(window.start());
        String endStr = formatter.format(window.end());

        return startStr + " – " + endStr;
    }
}
