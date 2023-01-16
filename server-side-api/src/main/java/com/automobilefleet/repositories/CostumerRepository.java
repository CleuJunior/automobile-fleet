package com.automobilefleet.repositories;

import com.automobilefleet.entities.Costumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CostumerRepository extends JpaRepository<Costumer, Long> {
    @Query("SELECT c FROM Costumer c WHERE c.name = :name")
    Optional<Costumer> findByName(@Param("name") String name);

    @Query("SELECT c FROM Costumer c WHERE c.name LIKE %:name%")
    Optional<List<Costumer>> findByNameList(@Param("name") String name);
}