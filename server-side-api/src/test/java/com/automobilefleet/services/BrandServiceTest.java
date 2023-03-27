package com.automobilefleet.services;

import com.automobilefleet.AplicationConfigTest;
import com.automobilefleet.api.reponse.BrandResponse;
import com.automobilefleet.entities.Brand;
import com.automobilefleet.repositories.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

@DisplayName("BrandServiceTest")
class BrandServiceTest extends AplicationConfigTest {

    @MockBean
    private BrandRepository  repository;

    @MockBean
    private ModelMapper mapper;

    @Autowired
    private BrandService service;

//    @Test
//    @DisplayName("Deve retornar uma lista de resposta de Brand")
//    void shouldReturnAListOfBrandResponse() {
//        System.out.println("Aqui");
//
//    }

    @Test
    @DisplayName("Deve retornar uma Brand com o ID passado por parametro")
    void shouldReturnBrandWithTheIdPassed() {
//        Long id = 1L;
//
//        Brand brand = Mockito.mock(Brand.class);
//        Mockito.when()
//
//        Optional<Brand> brand = Optional.of(makeBrand());
//        Mockito.when(this.repository.findById(ArgumentMatchers.eq(id))).thenReturn(brand);

    }

    @Test
    @DisplayName("Deve deletar caso haja o ID")
    void deleteShouldDeleteObjectWhenIdExists() {
        long existingId = 1L;
        this.repository.deleteById(existingId);

        Optional<Brand> result = this.repository.findById(existingId);

        Assertions.assertFalse(result.isPresent());
    }


}
