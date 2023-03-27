package com.automobilefleet.repositories;

import com.automobilefleet.AplicationConfigTest;
import com.automobilefleet.entities.Brand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;


@DisplayName("BrandServiceTest")
public class BrandRepositoryTest extends AplicationConfigTest {

    @Autowired
    private BrandRepository repository;

    @Test
    @DisplayName("Deve deletar caso haja o ID")
    void deleteShouldDeleteObjectWhenIdExists() {
        long existingId = 1L;
        this.repository.deleteById(existingId);

        Optional<Brand> result = this.repository.findById(existingId);

        Assertions.assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("Deve deletar caso haja o ID")
    void getdeleteShouldDeleteObjectWhenIdExists() {
        long existingId = 1L;

        Optional<Brand> result = this.repository.findById(existingId);

        Assertions.assertFalse(result.isPresent());
    }

}
