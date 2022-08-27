package com.automobilefleet.repositories;

import com.automobilefleet.entities.CarSpecifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarSpecificationRepository extends JpaRepository<CarSpecifications, Long> {
}