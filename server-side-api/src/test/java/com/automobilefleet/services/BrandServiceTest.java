package com.automobilefleet.services;

import com.automobilefleet.api.reponse.BrandResponse;
import com.automobilefleet.entities.Brand;
import com.automobilefleet.repositories.BrandRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BrandServiceTest {

    @Mock
    private BrandRepository repository;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private BrandService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListBrand() {
        // Cria uma lista de marcas de teste
        List<Brand> brands = Arrays.asList(new Brand(1L, "Marca 1"), new Brand(2L, "Marca 2"));

        // Configura o comportamento do repositório para retornar a lista de marcas de teste
        Mockito.when(repository.findAll()).thenReturn(brands);

        // Configura o comportamento do mapper para converter as marcas em BrandResponse
        Mockito.when(mapper.map(Mockito.any(Brand.class), Mockito.eq(BrandResponse.class)))
                .thenAnswer((invocation) -> {
                    Brand brand = invocation.getArgument(0);
                    return new BrandResponse(brand.getId(), brand.getName());
                });

        // Chama o método listBrand() e verifica se a lista de marcas retornada é igual à lista de marcas de teste
        List<BrandResponse> responseList = service.listBrand();
//        assertEquals(brands.size(), responseList.size());

        for (int i = 0; i < brands.size(); i++) {
            Brand brand = brands.get(i);
            BrandResponse response = responseList.get(i);

            assertEquals(brand.getId(), response.getId());
            assertEquals(brand.getName(), response.getName());
        }
    }
}

