package com.automobilefleet.repositories;

import com.automobilefleet.entities.CarSpecification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CarSpecificationRepository extends JpaRepository<CarSpecification, UUID> {
}