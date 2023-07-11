package com.automobilefleet.dao;

import com.automobilefleet.entities.Costumer;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CostumerJdbcDAO implements DAO<Costumer> {
    private static final Logger LOG = LoggerFactory.getLogger(CostumerJdbcDAO.class);
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Costumer> listObjects() {
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
            costumer.setUpdateAt(rs.getTimestamp(CostumerJdbcConstants.COSTUMER_COLUMN_UPDATE_AT).toLocalDateTime());
            return costumer;
        });
    }

    @Override
    public Costumer createObject(Costumer costumer) {
        Object[] args = {
                costumer.getName(),
                costumer.getBirthDate(),
                costumer.getEmail(),
                costumer.getDriverLicense(),
                costumer.getAddress(),
                costumer.getPhone(),
                costumer.getCreatedAt(),
                costumer.getUpdateAt()
        };

        int insert = this.jdbcTemplate.update(CostumerJdbcConstants.QUERY_INSERT, args);

        if (insert == 0) {
            return null;
        }

        return costumer;
    }

    @Override
    public Optional<Costumer> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteObject(Long id) {

    }
}
