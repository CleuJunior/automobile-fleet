package com.automobilefleet.repositories;

import com.automobilefleet.entities.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpecificationRepository extends JpaRepository<Specification, UUID> {
    Optional<Specification> findById(UUID id);
}