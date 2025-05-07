package com.automobilefleet.exceptions;

import org.springframework.dao.DataAccessException;

public class DriverLicenseIntegrityHandler extends DataAccessException {

    private final String constraintName;

    public DriverLicenseIntegrityHandler(String msg, Throwable cause, String constraintName) {
        super(msg, cause);
        this.constraintName = constraintName;
    }

    public String getConstraintName() {
        return constraintName;
    }
}
