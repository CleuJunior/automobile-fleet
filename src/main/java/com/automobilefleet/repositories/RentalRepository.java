package com.automobilefleet.repositories;

import com.automobilefleet.api.dto.projections.RentalInfo;
import com.automobilefleet.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RentalRepository extends JpaRepository<Rental, UUID> {

    @Query("""
            SELECT 
                r.id AS id,
                c.name AS customer,
                c.phone AS cellPhone,
                car.name AS car,
                car.licensePlate AS licensePlate,
                car.color AS color,
                b.name AS brand,
                r.startDate AS startDate,
                r.endDate AS endDate,
                FUNCTION('DATEDIFF', day, r.startDate, r.endDate) AS totalDays,
                r.total AS total
            FROM 
                Rental r
            JOIN 
                r.customer c
            JOIN 
                r.car car
            JOIN 
                car.brand b
            WHERE 
                r.id = :id
            """)
    Optional<RentalInfo> findRentalInfoById(@Param("id") UUID id);

}