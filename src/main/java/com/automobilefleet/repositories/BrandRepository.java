package com.automobilefleet.repositories;

import com.automobilefleet.entities.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BrandRepository extends JpaRepository<Brand, UUID> {

    @Query("SELECT b FROM Brand b WHERE b.deleted <> true")
    List<Brand> findAllNotDeleted();

    @Query("SELECT b FROM Brand b WHERE b.deleted <> true")
    Page<Brand> findAllNotDeleted(Pageable pageable);
}