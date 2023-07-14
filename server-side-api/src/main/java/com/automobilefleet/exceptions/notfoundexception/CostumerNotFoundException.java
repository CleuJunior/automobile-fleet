package com.automobilefleet.exceptions.notfoundexception;

import com.automobilefleet.exceptions.ExceptionsConstants;

public class CostumerNotFoundException extends NotFoundException {

    public CostumerNotFoundException() {
        super(ExceptionsConstants.COSTUMER_NOT_FOUND);
    }
}
