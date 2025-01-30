package com.automobilefleet.query;

import com.automobilefleet.entities.Customer;
import com.automobilefleet.search.CustomerSearch;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class CustomerQuery {

    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String DRIVE_LICENSE = "driverLicense";
    private static final String ADDRESS = "address";
    private static final String PHONE_NUMBER = "phone";
    private static final String BIRTHDATE = "birthdate";

    public Specification<Customer> query(CustomerSearch filter) {
        return queryField(NAME, filter.getName())
                .and(queryField(EMAIL, filter.getEmail()))
                .and(queryField(DRIVE_LICENSE, filter.getDriverLicense()))
                .and(queryField(ADDRESS, filter.getAddress()))
                .and(queryField(PHONE_NUMBER, filter.getPhone()))
                .and(queryField(filter.getBirthdate()));
    }

    private Specification<Customer> queryField(String field, String search) {
        return (root, query, builder) ->
                Optional.ofNullable(search)
                        .map(val -> builder.equal(root.get(field), val))
                        .orElse(null);
    }

    private Specification<Customer> queryField(LocalDate field) {
        return (root, query, builder) ->
                Optional.ofNullable(field)
                        .map(val -> builder.equal(root.get(BIRTHDATE), val))
                        .orElse(null);
    }
}