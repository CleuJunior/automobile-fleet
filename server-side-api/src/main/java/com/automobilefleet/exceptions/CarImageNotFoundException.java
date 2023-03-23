package com.automobilefleet.exceptions;

public class CarImageNotFoundException extends NotFoundException{

    public CarImageNotFoundException() {
        super("Image not found!");
    }
}
