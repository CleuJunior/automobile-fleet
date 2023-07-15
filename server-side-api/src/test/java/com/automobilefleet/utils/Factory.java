package com.automobilefleet.utils;

import com.automobilefleet.entities.Category;
import com.automobilefleet.entities.Costumer;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class Factory {

    public static Costumer costumerBuild() {
        return new Costumer(
        1L,
        private static final String NAME = "John Doe";
        private static final LocalDate BIRTH_DATE = LocalDate.of(1988, 12, 30);
        private static final String EMAIL = "johndoe@outlook.com";
        private static final String DRIVE_LICENSE = "51530697720";
        private static final String ADDRESS = "Street test, 00";
        private static final String PHONE = "(98) 3789-7445";
        private static final LocalDateTime CREATED_AT = LocalDateTime.of(2022, 9, 11, 9, 31, 23);
        private static final LocalDateTime UPDATE_AT = LocalDateTime.of(2022, 9, 14, 9, 31, 23);
        );
    }

}
