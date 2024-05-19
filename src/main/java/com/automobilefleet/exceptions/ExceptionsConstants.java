package com.automobilefleet.exceptions;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ExceptionsConstants {
    public static final String BRAND_NOT_FOUND = "Brand not found!";
    public static final String IMAGE_NOT_FOUND = "Image not found!";
    public static final String CAR_NOT_FOUND = "Car not found!";
    public static final String CAR_SPECIFICATION_NOT_FOUND = "Car specification not found!";
    public static final String CAR_IMAGE_NOT_FOUND = "Car specification not found!";
    public static final String CATEGORY_NOT_FOUND = "Category not found!";
    public static final String CUSTOMER_NOT_FOUND = "Customer not found!";
    public static final String RENTAL_NOT_FOUND = "Rental not found!";
    public static final String SPECIFICATION_NOT_FOUND = "specification not found!";
    public static final String DATE_CONSTRAIN_ERROR = "Something went wrong with the date format!";
    public static final String DRIVER_LICENSE_DUPLICATE = "Driving license already registered!";
    public static final String EMAIL_DUPLICATE = "Email already registered!";
    public static final String PHONE_REGEX_ERROR = "Phone format must be (12) 12345-1234";
    public static final String CAR_NAME_DUPLICATION = "Car username already registered!";
    public static final String CATEGORTY_NAME_DUPLICATION = "Category username already registered!";
    public static final String RENTAL_CANCELATION_POLYCI_ERROR = "Rental cannot be modify on the current day of rental!";
    public static final String CAR_AVAILABLE_POLYCI_ERROR = "Car is not available for rent!";

}
