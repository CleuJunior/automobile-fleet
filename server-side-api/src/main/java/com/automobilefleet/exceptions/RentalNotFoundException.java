package com.automobilefleet.exceptions;

public class RentalNotFoundException extends NotFoundException{

    public RentalNotFoundException() {
        super(ExceptionsConstants.RENTAL_NOT_FOUND);
    }
}
