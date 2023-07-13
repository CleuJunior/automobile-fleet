package com.automobilefleet.exceptions;

public class SpecificationNotFoundException extends NotFoundException {

    public SpecificationNotFoundException() {
        super(ExceptionsConstants.SPECIFICATION_NOT_FOUND);
    }
}
