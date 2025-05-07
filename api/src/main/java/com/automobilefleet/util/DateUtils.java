package com.automobilefleet.util;

import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

import static java.time.ZoneId.systemDefault;
import static java.time.format.DateTimeFormatter.ofPattern;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class DateUtils {

    public static String dateFormatToString(LocalDate date) {
        var formatter = ofPattern("dd-MM-yyyy");

        return date.format(formatter);
    }

    public static LocalDate localDateFromDate(Date date) {
        return date
                .toInstant()
                .atZone(systemDefault())
                .toLocalDate();
    }

}
