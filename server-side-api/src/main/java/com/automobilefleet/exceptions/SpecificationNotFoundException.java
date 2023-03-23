package com.automobilefleet.exceptions;

public class SpecificationNotFoundException extends NotFoundException{

    public SpecificationNotFoundException() {
        super("Specification not found!");
    }
}
