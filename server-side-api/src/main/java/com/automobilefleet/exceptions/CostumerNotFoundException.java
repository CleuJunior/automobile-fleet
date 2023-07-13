package com.automobilefleet.exceptions;

public class CostumerNotFoundException extends NotFoundException {

    public CostumerNotFoundException() {
        super(ExceptionsConstants.COSTUMER_NOT_FOUND);
    }
}
