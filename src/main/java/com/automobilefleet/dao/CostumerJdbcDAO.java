package com.automobilefleet.dao;

import com.automobilefleet.entities.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CostumerJdbcDAO implements DAO<Customer> {
    private final JdbcTemplate jdbcTemplate;
//    private final static RowMapper<Customer> ROW_MAPPER_COSTUMER = (rs, row) -> {
//        Customer customer = new Customer();
//        customer.setId(rs.getLong(CostumerJdbcConstants.COSTUMER_COLUMN_ID));
//        customer.setName(rs.getString(CostumerJdbcConstants.COSTUMER_COLUMN_NAME));
//        customer.setBirthDate(rs.getDate(CostumerJdbcConstants.COSTUMER_COLUMN_BIRTH_DATE).toLocalDate());
//        customer.setEmail(rs.getString(CostumerJdbcConstants.COSTUMER_COLUMN_EMAIL));
//        customer.setDriverLicense(rs.getString(CostumerJdbcConstants.COSTUMER_COLUMN_DRIVER_LICENSE));
//        customer.setAddress(rs.getString(CostumerJdbcConstants.COSTUMER_COLUMN_ADDRESS));
//        customer.setPhone(rs.getString(CostumerJdbcConstants.COSTUMER_COLUMN_PHONE_NUMBER));
//        customer.setCreatedAt(rs.getTimestamp(CostumerJdbcConstants.COSTUMER_COLUMN_CREATED_AT).toLocalDateTime());
//        customer.setUpdatedAt(rs.getTimestamp(CostumerJdbcConstants.COSTUMER_COLUMN_UPDATED_AT).toLocalDateTime());
//        return customer;
//    };

    @Override
    public List<Customer> findAll() {
        return this.jdbcTemplate.query(CostumerJdbcConstants.QUERY, this.getRowMapperCostumer());
    }

    @Override
    public int save(Customer customer) {
        return this.jdbcTemplate.update(
                CostumerJdbcConstants.INSERT,
                customer.getName(),
                customer.getBirthdate(),
                customer.getEmail(),
                customer.getDriverLicense(),
                customer.getAddress(),
                customer.getPhone(),
                customer.getCreatedAt(),
                customer.getUpdatedAt()
        );

        //TODO build a Exception
//        if (insert == 0) {
//            return null;
//        }
    }

    @Override
    public Optional<Customer> getById(Long id) {
        Customer customer = null;


//        System.out.println(this.jdbcTemplate.queryForObject(CostumerJdbcConstants.GET_BY_ID, this.getRowMapperCostumer()), );

        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }

    private RowMapper<Customer> getRowMapperCostumer() {
        return (rs, row) -> {
            Customer customer = new Customer();
            customer.setId(UUID.fromString(rs.getString(CostumerJdbcConstants.COSTUMER_COLUMN_ID)));
            customer.setName(rs.getString(CostumerJdbcConstants.COSTUMER_COLUMN_NAME));
            customer.setBirthdate(rs.getDate(CostumerJdbcConstants.COSTUMER_COLUMN_BIRTH_DATE).toLocalDate());
            customer.setEmail(rs.getString(CostumerJdbcConstants.COSTUMER_COLUMN_EMAIL));
            customer.setDriverLicense(rs.getString(CostumerJdbcConstants.COSTUMER_COLUMN_DRIVER_LICENSE));
            customer.setAddress(rs.getString(CostumerJdbcConstants.COSTUMER_COLUMN_ADDRESS));
            customer.setPhone(rs.getString(CostumerJdbcConstants.COSTUMER_COLUMN_PHONE_NUMBER));
            customer.setCreatedAt(rs.getTimestamp(CostumerJdbcConstants.COSTUMER_COLUMN_CREATED_AT).toLocalDateTime());
            customer.setUpdatedAt(rs.getTimestamp(CostumerJdbcConstants.COSTUMER_COLUMN_UPDATED_AT).toLocalDateTime());
            return customer;
        };
    }
}
