package com.automobilefleet.exceptions;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ExceptionsConstants {
    public static final String DATE_CONSTRAIN_ERROR = "Something went wrong with the date format!";
    public static final String DRIVER_LICENSE_DUPLICATE = "Driving license already registered!";
    public static final String EMAIL_DUPLICATE = "Email already registered!";
    public static final String PHONE_REGEX_ERROR = "Phone format must be (12) 12345-1234";
    public static final String CAR_NAME_DUPLICATION = "Car username already registered!";
    public static final String CATEGORTY_NAME_DUPLICATION = "Category username already registered!";
    public static final String RENTAL_CANCELLATION_POLICY_ERROR = "Rental cannot be modify on the current day of rental!";

}
