package com.automobilefleet.dao;

public class CostumerJdbcConstants {

    private CostumerJdbcConstants() { }
    public static final String QUERY_SELECT = "SELECT * FROM costumer_entity";
    public static final String QUERY_INSERT = "INSERT INTO costumer_entity(name, birth_date, email, driver_license, " +
            "address, phone_number, created_at, update_at) values(?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String COSTUMER_COLUMN_ID = "_id";
    public static final String COSTUMER_COLUMN_NAME = "name";
    public static final String COSTUMER_COLUMN_BIRTH_DATE = "birth_date";
    public static final String COSTUMER_COLUMN_EMAIL = "email";
    public static final String COSTUMER_COLUMN_DRIVER_LICENSE = "driver_license";
    public static final String COSTUMER_COLUMN_ADDRESS = "address";
    public static final String COSTUMER_COLUMN_PHONE_NUMBER = "phone_number";
    public static final String COSTUMER_COLUMN_CREATED_AT = "created_at";
    public static final String COSTUMER_COLUMN_UPDATE_AT = "update_at";

}
