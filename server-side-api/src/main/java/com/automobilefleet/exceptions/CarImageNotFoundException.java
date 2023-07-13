package com.automobilefleet.exceptions;

public class CarImageNotFoundException extends NotFoundException {

    public CarImageNotFoundException() {
        super(ExceptionsConstants.IMAGE_NOT_FOUND);
    }
}
