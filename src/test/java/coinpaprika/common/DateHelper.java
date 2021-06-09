package coinpaprika.common;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateHelper {
    public static String getDateTimeNow(DateTimeFormatter dateTimeFormatter) {
        ZonedDateTime zdt = ZonedDateTime.now();

        return zdt.format(dateTimeFormatter);
    }
}
