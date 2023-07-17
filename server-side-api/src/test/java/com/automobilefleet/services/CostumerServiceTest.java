package com.automobilefleet.services;

import com.automobilefleet.api.request.CostumerRequest;
import com.automobilefleet.api.response.CostumerResponse;
import com.automobilefleet.entities.Costumer;
import com.automobilefleet.exceptions.ExceptionsConstants;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.repositories.CostumerRepository;
import com.automobilefleet.utils.costumer.CostumerFactoryUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CostumerServiceTest  {
    @InjectMocks
    private CostumerService costumerService;
    @Mock
    private CostumerRepository costumerRepository;
    @Mock
    private ModelMapper mapper;
    private Costumer costumer;
    private CostumerRequest costumerRequest;
    private CostumerResponse costumerResponse;
    private static final long ID = 1L;
    private static final long NON_EXISTING_ID = 99L;

    @BeforeEach
    void setUp() {
        this.costumer = CostumerFactoryUtils.costumerBuilRaimunda();
        this.costumerResponse = CostumerFactoryUtils.costumerResponseBuildRaimunda();
        this.costumerRequest = CostumerFactoryUtils.costumerRequestBuildRaimunda();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldReturnAListWithThreeElement() {
        final List<Costumer> costumerList = List.of(
                CostumerFactoryUtils.costumerBuilRaimunda(),
                CostumerFactoryUtils.costumerBuildGustavo(),
                CostumerFactoryUtils.costumerBuildMaercela()
        );

        final CostumerResponse expectedRaimunda = CostumerFactoryUtils.costumerResponseBuildRaimunda();
        final CostumerResponse expectedGustavo = CostumerFactoryUtils.costumerResponseBuildGustavo();
        final CostumerResponse expectedMacercela = CostumerFactoryUtils.costumerReponseBuildMaercela();

        Mockito.when(this.costumerRepository.findAll()).thenReturn(costumerList);
        Mockito.when(this.mapper.map(costumerList.get(0), CostumerResponse.class)).thenReturn(expectedRaimunda);
        Mockito.when(this.mapper.map(costumerList.get(1), CostumerResponse.class)).thenReturn(expectedGustavo);
        Mockito.when(this.mapper.map(costumerList.get(2), CostumerResponse.class)).thenReturn(expectedMacercela);

        final List<CostumerResponse> actual = this.costumerService.listCostumer();

        // Assert list has data
        Assertions.assertNotNull(actual);
        Assertions.assertFalse(actual.isEmpty());
        Assertions.assertEquals(3, actual.size());

        this.assertionsDatas(expectedRaimunda, actual.get(0));
        this.assertionsDatas(expectedGustavo, actual.get(1));
        this.assertionsDatas(expectedMacercela, actual.get(2));
    }

    private void assertionsDatas(CostumerResponse expected, CostumerResponse actual) {
        Assertions.assertEquals(expected.getId(), actual.getId());
        Assertions.assertEquals(expected.getName(), actual.getName());
        Assertions.assertEquals(expected.getBirthDate(), actual.getBirthDate());
        Assertions.assertEquals(expected.getEmail(), actual.getEmail());
        Assertions.assertEquals(expected.getDriverLicense(), actual.getDriverLicense());
        Assertions.assertEquals(expected.getAddress(), actual.getAddress());
        Assertions.assertEquals(expected.getPhone(), actual.getPhone());
        Assertions.assertEquals(expected.getCreatedAt(), actual.getCreatedAt());
        Assertions.assertEquals(expected.getUpdatedAt(), actual.getUpdatedAt());
        Assertions.assertEquals(expected.getUpdatedAt(), actual.getUpdatedAt());
    }

    @Test
    void shouldReturnOneCostumerById() {
        Mockito.when(this.costumerRepository.findById(ID)).thenReturn(Optional.ofNullable(this.costumer));
        Mockito.when(this.mapper.map(this.costumer, CostumerResponse.class)).thenReturn(this.costumerResponse);

        CostumerResponse expected =  this.costumerService.getCostumerById(ID);
        Assertions.assertNotNull(expected);
        Assertions.assertDoesNotThrow(() -> new NotFoundException(ExceptionsConstants.COSTUMER_NOT_FOUND));
        Assertions.assertInstanceOf(CostumerResponse.class, expected);
        Assertions.assertEquals(expected, this.costumerResponse);

        Mockito.verify(this.costumerRepository).findById(ID);
        Mockito.verifyNoMoreInteractions(this.costumerRepository);
    }

    @Test
    void shouldThrowsCostumerNotFoundExceptionWhenIdNonExisting() {
        Mockito.when(this.costumerRepository.findById(NON_EXISTING_ID)).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> this.costumerService.getCostumerById(NON_EXISTING_ID));

        Mockito.verify(this.costumerRepository).findById(NON_EXISTING_ID);
        Mockito.verifyNoMoreInteractions(this.costumerRepository);
    }

    @Test
    void shouldSaveCostumerWhenCallingSaveCostumer() {
        Mockito.when(this.costumerRepository.save(this.costumer)).thenReturn(this.costumer);
        Mockito.when(this.mapper.map(this.costumer, CostumerResponse.class)).thenReturn(this.costumerResponse);
        Mockito.when(this.mapper.map(this.costumerRequest, Costumer.class)).thenReturn(this.costumer);

        CostumerResponse expected = this.costumerService.saveCostumer(this.costumerRequest);

        Assertions.assertNotNull(expected);
        Assertions.assertInstanceOf(CostumerResponse.class, expected);
        Assertions.assertEquals(expected, this.costumerResponse);
    }

    @Test
    void shouldUpdateCostumerWhenCallingUpdate() {
        String nameUpdate = "Update Working";
        this.costumerRequest.setName(nameUpdate);
        this.costumerResponse.setName(nameUpdate);

        // Configurar o comportamento dos mocks
        Mockito.when(this.costumerRepository.findById(ID)).thenReturn(Optional.of(this.costumer));
        Mockito.when(this.mapper.map(this.costumer, CostumerResponse.class)).thenReturn(this.costumerResponse);

        // Chamar o método a ser testado
        CostumerResponse result = this.costumerService.updateCostumer(ID, this.costumerRequest);

        // Verificar o resultado
        Assertions.assertNotNull(result);
        Assertions.assertEquals(this.costumerResponse.getId(), result.getId());
        Assertions.assertEquals(this.costumerResponse.getName(), result.getName());
        Assertions.assertSame(this.costumerResponse, result);

        // Verificar as interações dos mocks
        Mockito.verify(this.costumerRepository).findById(ID);
        Mockito.verify(costumerRepository).save(this.costumer);
        Mockito.verify(this.mapper).map(this.costumer, CostumerResponse.class);
        Mockito.verifyNoMoreInteractions(this.costumerRepository, this.mapper);
    }
}