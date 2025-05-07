package com.automobilefleet.repositories;

import com.automobilefleet.entities.CarComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CarCommentRepository extends JpaRepository<CarComment, UUID> {
}