package com.automobilefleet.exceptions;

public class CarNotFoundException extends NotFoundException {

    public CarNotFoundException() {
        super(NotFoundExceptionsConstants.CAR_NOT_FOUND);
    }
}
