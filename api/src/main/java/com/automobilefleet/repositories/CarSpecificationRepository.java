package com.automobilefleet.repositories;

import com.automobilefleet.api.dto.projections.CarSpecificationInfo;
import com.automobilefleet.entities.CarSpecification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CarSpecificationRepository extends JpaRepository<CarSpecification, UUID> {

    @Query("""
            SELECT 
                cs.id AS id,
                c.name AS carName,
                s.name AS specificationName,
                s.description AS specificationDescription
            FROM 
                CarSpecification cs
            JOIN 
                cs.car c
            JOIN 
                cs.specification s
            WHERE 
                cs.id = :id
            """)
    Optional<CarSpecificationInfo> carSpecificationInfo(@Param("id") UUID id);

}