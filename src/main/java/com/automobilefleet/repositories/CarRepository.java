package com.automobilefleet.repositories;

import com.automobilefleet.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CarRepository extends JpaRepository<Car, UUID> {
    List<Car> findByAvailable(Boolean available);

    List<Car> findCarsByBrand_Name(String name);

}