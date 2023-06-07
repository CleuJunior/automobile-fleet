package com.automobilefleet.exceptions;

public class CarImageNotFoundException extends NotFoundException {

    public CarImageNotFoundException() {
        super(NotFoundExceptionsConstants.IMAGE_NOT_FOUND);
    }
}
