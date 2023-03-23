package com.automobilefleet.exceptions;

public class RentalNotFoundException extends NotFoundException{

    public RentalNotFoundException() {
        super("Rental not found!");
    }
}
