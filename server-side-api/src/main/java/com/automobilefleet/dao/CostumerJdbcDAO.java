package com.automobilefleet.dao;

import com.automobilefleet.entities.Costumer;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CostumerJdbcDAO implements DAO<Costumer> {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Costumer> findAll() {
        return this.jdbcTemplate.query(CostumerJdbcConstants.QUERY_SELECT, (rs, row) -> {
            Costumer costumer = new Costumer();
            costumer.setId(rs.getLong(CostumerJdbcConstants.COSTUMER_COLUMN_ID));
            costumer.setName(rs.getString(CostumerJdbcConstants.COSTUMER_COLUMN_NAME));
            costumer.setBirthDate(rs.getDate(CostumerJdbcConstants.COSTUMER_COLUMN_BIRTH_DATE).toLocalDate());
            costumer.setEmail(rs.getString(CostumerJdbcConstants.COSTUMER_COLUMN_EMAIL));
            costumer.setDriverLicense(rs.getString(CostumerJdbcConstants.COSTUMER_COLUMN_DRIVER_LICENSE));
            costumer.setAddress(rs.getString(CostumerJdbcConstants.COSTUMER_COLUMN_ADDRESS));
            costumer.setPhone(rs.getString(CostumerJdbcConstants.COSTUMER_COLUMN_PHONE_NUMBER));
            costumer.setCreatedAt(rs.getTimestamp(CostumerJdbcConstants.COSTUMER_COLUMN_CREATED_AT).toLocalDateTime());
            costumer.setUpdatedAt(rs.getTimestamp(CostumerJdbcConstants.COSTUMER_COLUMN_UPDATED_AT).toLocalDateTime());
            return costumer;
        });
    }

    @Override
    public int save(Costumer costumer) {
        return this.jdbcTemplate.update(
                CostumerJdbcConstants.QUERY_INSERT,
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
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }
}
