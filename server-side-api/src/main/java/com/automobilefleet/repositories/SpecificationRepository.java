package com.automobilefleet.repositories;

import com.automobilefleet.entities.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecificationRepository extends JpaRepository<Specification, Long> {
}