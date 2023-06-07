package com.automobilefleet.exceptions;

public class RentalNotFoundException extends NotFoundException{

    public RentalNotFoundException() {
        super(NotFoundExceptionsConstants.RENTAL_NOT_FOUND);
    }
}
