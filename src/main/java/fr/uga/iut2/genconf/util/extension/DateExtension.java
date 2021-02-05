package fr.uga.iut2.genconf.util.extension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateExtension {
    public static String getAs_ISO_8061(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
