package com.automobilefleet.repositories;

import com.automobilefleet.entities.Car;
import com.automobilefleet.entities.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CarRepository extends JpaRepository<Car, UUID> {
    Optional<Car> findById(UUID id);
    List<Car> findByAvailable(Boolean available);
    List<Car> findCarsByBrand_Name(String name);

}