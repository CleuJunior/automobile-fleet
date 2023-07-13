package com.automobilefleet.exceptions;

public class CarSpecificationsNotFoundException extends NotFoundException {

    public CarSpecificationsNotFoundException() {
        super(ExceptionsConstants.CAR_SPECIFICATION_NOT_FOUND);
    }
}
