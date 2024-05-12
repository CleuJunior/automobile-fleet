package com.automobilefleet.exceptions;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
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
    public static final String DRIVER_LICENSE_DUPLICATE = "Duplicate key value violates unique constraint for driver lincese!";
    public static final String EMAIL_DUPLICATE = "Duplicate key value violates unique constraint for email!";
    public static final String PHONE_REGEX_ERROR = "Phone format must be (12) 12345-1234";

}
