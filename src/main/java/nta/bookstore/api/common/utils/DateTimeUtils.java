package nta.bookstore.api.common.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {
    //convert string to Timestamp
    public static Timestamp convertStringToTimestamp(String string) {
        return Timestamp.valueOf(string);
    }

    // get time now
    public static Timestamp getNow() {
        return new Timestamp(System.currentTimeMillis());
    }

    //compare 2 value Timestamp
    public static Boolean compareTimestamp(Timestamp time1, Timestamp time2) {
        return time1.getTime() > time2.getTime();
    }

    public static String getTimeStamp() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
    }


}
