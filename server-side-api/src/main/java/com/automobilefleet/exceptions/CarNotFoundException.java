package com.automobilefleet.exceptions;

public class CarNotFoundException extends NotFoundException{

    public CarNotFoundException() {
        super("Car not found!");
    }
}
