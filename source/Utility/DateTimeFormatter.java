package source.Utility;

import java.time.LocalDate;
import java.time.format.FormatStyle;

/**
 * The DateTimeFormatter class formats date times to the local Singapore context.
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/20/2023
 */
public class DateTimeFormatter {
    /**
     * Formats a local date to a nicer format
     *
     * @return formatted local date in localized cleaner format
     */
    public static String formatDateTimeToLocal(LocalDate date) {
        return date.format(java.time.format.DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
    }
}