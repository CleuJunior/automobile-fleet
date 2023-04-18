package com.automobilefleet.services;

import com.automobilefleet.api.reponse.BrandResponse;
import com.automobilefleet.entities.Brand;
import com.automobilefleet.repositories.BrandRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
class BrandServiceTest {

    @InjectMocks
    private BrandService brandService;

    @Mock
    private BrandRepository brandRepository;

    @Mock
    private ModelMapper mapper;

    @Test
    @DisplayName("Should return a brand that was saved.")
    void shouldReturnABrandWhenCallingGetBrand() {
        /* Cria uma Brand */
        Brand brand = new Brand(1L, "Brand 1");

        /* Configura o comportamento do objeto de repositório simulado */
        Mockito.when(this.brandRepository.findById(1L).thenReturn(Optional.of(brand));

        /* Cria uma lista simulada de objetos BrandResponse */
        List<BrandResponse> expectedResponses = new ArrayList<>();
        expectedResponses.add(new BrandResponse(1L, "Brand 1"));
        expectedResponses.add(new BrandResponse(2L, "Brand 2"));
        expectedResponses.add(new BrandResponse(3L, "Brand 3"));

        /* Configura o comportamento do objeto de mapeador de modelo simulado */
        brands.forEach(
                b -> Mockito.when(this.mapper.map(b, BrandResponse.class))
                        .thenReturn(expectedResponses.get(brands.indexOf(b)))
        );

        /* Chama o método listBrand() e verifica se a lista retornada é igual à lista simulada de objetos BrandResponse */
        List<BrandResponse> actualResponses = this.brandService.listBrand();
        Assertions.assertEquals(expectedResponses, actualResponses);
    }

    @Test
    @DisplayName("Should return a list of brands.")
    void shouldReturnListOfBrandsWhenCallingFindAll() {
        List<Brand> brands = List.of(
                new Brand(1L, "Brand 1"),
                new Brand(2L, "Brand 2"),
                new Brand(3L, "Brand 3")
        );

        Mockito.when(this.brandRepository.findAll()).thenReturn(brands);

        List<BrandResponse> expectedResponses = new ArrayList<>();
        expectedResponses.add(new BrandResponse(1L, "Brand 1"));
        expectedResponses.add(new BrandResponse(2L, "Brand 2"));
        expectedResponses.add(new BrandResponse(3L, "Brand 3"));

        /* Configura o comportamento do objeto de mapeador de modelo simulado */
        brands.forEach(
                b -> Mockito.when(this.mapper.map(b, BrandResponse.class))
                        .thenReturn(expectedResponses.get(brands.indexOf(b)))
        );

        List<BrandResponse> actualResponses = this.brandService.listBrand();
        Assertions.assertEquals(expectedResponses, actualResponses);
    }
}
