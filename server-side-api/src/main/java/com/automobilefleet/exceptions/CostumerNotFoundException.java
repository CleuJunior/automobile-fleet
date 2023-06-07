package com.automobilefleet.exceptions;

public class CostumerNotFoundException extends NotFoundException {

    public CostumerNotFoundException() {
        super(NotFoundExceptionsConstants.COSTUMER_NOT_FOUND);
    }
}
