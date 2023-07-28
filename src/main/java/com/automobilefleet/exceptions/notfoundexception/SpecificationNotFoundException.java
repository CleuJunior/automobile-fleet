package com.automobilefleet.exceptions.notfoundexception;

import com.automobilefleet.exceptions.ExceptionsConstants;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;

public class SpecificationNotFoundException extends NotFoundException {

    public SpecificationNotFoundException() {
        super(ExceptionsConstants.SPECIFICATION_NOT_FOUND);
    }
}
