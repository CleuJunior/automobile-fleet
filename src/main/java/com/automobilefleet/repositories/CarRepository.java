package com.automobilefleet.repositories;

import com.automobilefleet.entities.Car;
import com.automobilefleet.projections.CarInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CarRepository extends JpaRepository<Car, UUID> {
    List<Car> findByAvailable(Boolean available);

    List<Car> findCarsByBrand_Name(String name);

    @Query("""
    SELECT 
        c.id AS id, 
        c.name AS name, 
        c.description AS description, 
        c.licensePlate AS licensePlate, 
        c.color AS color, 
        b.name AS brand, 
        cat.name AS category
    FROM 
        Car c
    JOIN 
        c.brand b
    JOIN 
        c.category cat
    WHERE 
        c.id = :id
    """)
    Optional<CarInfo> getCarInfo(@Param("id") UUID id);

}