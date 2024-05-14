package com.automobilefleet.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.automobilefleet.exceptions.ConstraintViolationEnum.ConstraintViolationConstants.CAR_NAME_KEY;
import static com.automobilefleet.exceptions.ConstraintViolationEnum.ConstraintViolationConstants.CATEGORTY_NAME_KEY;
import static com.automobilefleet.exceptions.ConstraintViolationEnum.ConstraintViolationConstants.DRIVER_LICENSE_KEY;
import static com.automobilefleet.exceptions.ConstraintViolationEnum.ConstraintViolationConstants.EMAIL_KEY;
import static com.automobilefleet.exceptions.ExceptionsConstants.CAR_NAME_DUPLICATION;
import static com.automobilefleet.exceptions.ExceptionsConstants.CATEGORTY_NAME_DUPLICATION;
import static com.automobilefleet.exceptions.ExceptionsConstants.DRIVER_LICENSE_DUPLICATE;
import static com.automobilefleet.exceptions.ExceptionsConstants.EMAIL_DUPLICATE;
import static java.util.Arrays.stream;
import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor
@Getter
public enum ConstraintViolationEnum {

    DRIVER_LICENSE_VIOLATION(DRIVER_LICENSE_KEY, DRIVER_LICENSE_DUPLICATE),
    EMAIL_VIOLATION(EMAIL_KEY, EMAIL_DUPLICATE),
    CAR_NAME_VIOLATION(CAR_NAME_KEY, CAR_NAME_DUPLICATION),
    CATEGORTY_NAME_VIOLATION(CATEGORTY_NAME_KEY, CATEGORTY_NAME_DUPLICATION);

    private final String key;
    private final String message;

    public static String constraintFromKey(String key) {
        return stream(ConstraintViolationEnum.values())
                .filter(constraint -> constraint.getKey().equals(key))
                .map(ConstraintViolationEnum::getMessage)
                .findFirst()
                .orElse("Unknown key");
    }

    @NoArgsConstructor(access = PRIVATE)
    protected static class ConstraintViolationConstants {
        protected static final String DRIVER_LICENSE_KEY = "customer_entity_driver_license_key";
        protected static final String EMAIL_KEY = "customer_entity_email_key";
        protected static final String CAR_NAME_KEY = "car_entity_car_name_key";
        protected static final String CATEGORTY_NAME_KEY = "category_entity_name_key";
    }
}
