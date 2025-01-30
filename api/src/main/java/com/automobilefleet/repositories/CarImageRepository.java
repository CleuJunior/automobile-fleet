package com.automobilefleet.repositories;

import com.automobilefleet.entities.CarImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CarImageRepository extends JpaRepository<CarImage, UUID> {
}
