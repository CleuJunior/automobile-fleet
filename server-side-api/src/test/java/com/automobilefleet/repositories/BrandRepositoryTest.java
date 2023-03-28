package com.automobilefleet.repositories;

import com.automobilefleet.api.reponse.BrandResponse;
import com.automobilefleet.api.request.BrandRequest;
import com.automobilefleet.entities.Brand;
import com.automobilefleet.services.BrandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class BrandServiceTest {

    @Mock
    private BrandRepository brandRepository;

    @InjectMocks
    private BrandService brandService;

    @InjectMocks
    private ModelMapper mapper;

    private Brand brand;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        brand = new Brand(null, "Test Brand");
    }

    @Test
    @DisplayName("Should save a brand with generated ID and creation date")
    void shouldSaveBrand() {
        // Configuração do mock do repository
        when(brandRepository.save(any(Brand.class)))
                .thenAnswer(invocation -> invocation.<Brand>getArgument(0));

        BrandRequest request = this.mapper.map(brand, BrandRequest.class);

        // Execução do serviço
        BrandResponse savedBrand = this.brandService.saveBrand(request);

        // Verificação do resultado
        assertNotNull(savedBrand.getId());
        assertNotNull(savedBrand.getCreatedAt());
    }

}

