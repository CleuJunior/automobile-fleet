package com.automobilefleet.exceptions;

public class SpecificationNotFoundException extends NotFoundException {

    public SpecificationNotFoundException() {
        super(NotFoundExceptionsConstants.SPECIFICATION_NOT_FOUND);
    }
}
