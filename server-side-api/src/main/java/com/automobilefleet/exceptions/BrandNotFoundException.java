package com.automobilefleet.exceptions;

public class BrandNotFoundException extends NotFoundException{

    public BrandNotFoundException() {
        super("Brand not found!");
    }
}
