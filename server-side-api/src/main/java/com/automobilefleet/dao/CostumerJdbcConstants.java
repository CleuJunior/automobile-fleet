package com.automobilefleet.dao;

public class CostumerJdbcConstants {

    private CostumerJdbcConstants() { }
    public static final String QUERY = "SELECT * FROM costumer_entity";
    public static final String INSERT
            = "INSERT INTO costumer_entity(name, birthdate, email, driver_license, address, phone_number, created_at, updated_at) " +
            "values(?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String GET_BY_ID = "SELECT * FROM costumer_entity WHERE _id = ?";
    public static final String COSTUMER_COLUMN_ID = "_id";
    public static final String COSTUMER_COLUMN_NAME = "name";
    public static final String COSTUMER_COLUMN_BIRTH_DATE = "birthdate";
    public static final String COSTUMER_COLUMN_EMAIL = "email";
    public static final String COSTUMER_COLUMN_DRIVER_LICENSE = "driver_license";
    public static final String COSTUMER_COLUMN_ADDRESS = "address";
    public static final String COSTUMER_COLUMN_PHONE_NUMBER = "phone_number";
    public static final String COSTUMER_COLUMN_CREATED_AT = "created_at";
    public static final String COSTUMER_COLUMN_UPDATED_AT = "updated_at";

}
