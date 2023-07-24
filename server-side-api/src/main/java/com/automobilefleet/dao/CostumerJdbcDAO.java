package com.automobilefleet.dao;

import com.automobilefleet.entities.Costumer;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CostumerJdbcDAO implements DAO<Costumer> {
    private final JdbcTemplate jdbcTemplate;
//    private final static RowMapper<Costumer> ROW_MAPPER_COSTUMER = (rs, row) -> {
//        Costumer costumer = new Costumer();
//        costumer.setId(rs.getLong(CostumerJdbcConstants.COSTUMER_COLUMN_ID));
//        costumer.setName(rs.getString(CostumerJdbcConstants.COSTUMER_COLUMN_NAME));
//        costumer.setBirthDate(rs.getDate(CostumerJdbcConstants.COSTUMER_COLUMN_BIRTH_DATE).toLocalDate());
//        costumer.setEmail(rs.getString(CostumerJdbcConstants.COSTUMER_COLUMN_EMAIL));
//        costumer.setDriverLicense(rs.getString(CostumerJdbcConstants.COSTUMER_COLUMN_DRIVER_LICENSE));
//        costumer.setAddress(rs.getString(CostumerJdbcConstants.COSTUMER_COLUMN_ADDRESS));
//        costumer.setPhone(rs.getString(CostumerJdbcConstants.COSTUMER_COLUMN_PHONE_NUMBER));
//        costumer.setCreatedAt(rs.getTimestamp(CostumerJdbcConstants.COSTUMER_COLUMN_CREATED_AT).toLocalDateTime());
//        costumer.setUpdatedAt(rs.getTimestamp(CostumerJdbcConstants.COSTUMER_COLUMN_UPDATED_AT).toLocalDateTime());
//        return costumer;
//    };

    @Override
    public List<Costumer> findAll() {
        return this.jdbcTemplate.query(CostumerJdbcConstants.QUERY, this.getRowMapperCostumer());
    }

    @Override
    public int save(Costumer costumer) {
        return this.jdbcTemplate.update(
                CostumerJdbcConstants.INSERT,
                costumer.getName(),
                costumer.getBirthDate(),
                costumer.getEmail(),
                costumer.getDriverLicense(),
                costumer.getAddress(),
                costumer.getPhone(),
                costumer.getCreatedAt(),
                costumer.getUpdatedAt()
        );

        //TODO build a Exception
//        if (insert == 0) {
//            return null;
//        }
    }

    @Override
    public Optional<Costumer> getById(Long id) {
        Costumer costumer = null;


//        System.out.println(this.jdbcTemplate.queryForObject(CostumerJdbcConstants.GET_BY_ID, this.getRowMapperCostumer()), );

        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }

    private RowMapper<Costumer> getRowMapperCostumer() {
        return (rs, row) -> {
            Costumer costumer = new Costumer();
            costumer.setId(UUID.fromString(rs.getString(CostumerJdbcConstants.COSTUMER_COLUMN_ID)));
            costumer.setName(rs.getString(CostumerJdbcConstants.COSTUMER_COLUMN_NAME));
            costumer.setBirthDate(rs.getDate(CostumerJdbcConstants.COSTUMER_COLUMN_BIRTH_DATE).toLocalDate());
            costumer.setEmail(rs.getString(CostumerJdbcConstants.COSTUMER_COLUMN_EMAIL));
            costumer.setDriverLicense(rs.getString(CostumerJdbcConstants.COSTUMER_COLUMN_DRIVER_LICENSE));
            costumer.setAddress(rs.getString(CostumerJdbcConstants.COSTUMER_COLUMN_ADDRESS));
            costumer.setPhone(rs.getString(CostumerJdbcConstants.COSTUMER_COLUMN_PHONE_NUMBER));
            costumer.setCreatedAt(rs.getTimestamp(CostumerJdbcConstants.COSTUMER_COLUMN_CREATED_AT).toLocalDateTime());
            costumer.setUpdatedAt(rs.getTimestamp(CostumerJdbcConstants.COSTUMER_COLUMN_UPDATED_AT).toLocalDateTime());
            return costumer;
        };
    }
}
