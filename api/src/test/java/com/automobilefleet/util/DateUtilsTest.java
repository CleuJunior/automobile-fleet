package com.automobilefleet.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import static com.automobilefleet.util.DateUtils.dateFormatToString;
import static com.automobilefleet.util.DateUtils.localDateFromDate;
import static org.assertj.core.api.BDDAssertions.then;

class DateUtilsTest {

    @Test
    void shouldReturnStringFromDate() {
        var localDate = LocalDate.of(2019, 3, 16);
        var result = dateFormatToString(localDate);

        then(result).isEqualTo("16-03-2019");
    }

    @Test
    void shouldReturnLocalDateFromDate() {
        // 2024/06/09
        var date = new Date(1717964717189L);
        var result = localDateFromDate(date);

        then(result).isInstanceOf(LocalDate.class);
        then(result.getYear()).isEqualTo(2024);
        then(result.getMonthValue()).isEqualTo(6);
        then(result.getDayOfMonth()).isEqualTo(9);
    }
}