package com.automobilefleet.exceptions;

public class CarSpecificationsNotFoundException extends NotFoundException{

    public CarSpecificationsNotFoundException() {
        super("Car Specification not found!");
    }
}
