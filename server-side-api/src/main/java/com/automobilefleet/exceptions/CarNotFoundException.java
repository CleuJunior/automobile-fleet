package com.automobilefleet.exceptions;

public class CarNotFoundException extends NotFoundException {

    public CarNotFoundException() {
        super(ExceptionsConstants.CAR_NOT_FOUND);
    }
}
