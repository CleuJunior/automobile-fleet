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
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.automobilefleet.utils.costumer.CostumersTemplate.DRIVER_LICENSE_GUSTAVO_RAFAEL;
import static com.automobilefleet.utils.costumer.CostumersTemplate.DRIVER_LICENSE_MACERLA_SOUZA;
import static com.automobilefleet.utils.costumer.CostumersTemplate.DRIVER_LICENSE_RAIMUNDA_REGINA;
import static com.automobilefleet.utils.costumer.CostumersTemplate.EMAIL_GUSTAVO_RAFAEL;
import static com.automobilefleet.utils.costumer.CostumersTemplate.EMAIL_MACERLA_SOUZA;
import static com.automobilefleet.utils.costumer.CostumersTemplate.EMAIL_RAIMUNDA_REGINA;
import static com.automobilefleet.utils.costumer.CostumersTemplate.NAME_GUSTAVO_RAFAEL;
import static com.automobilefleet.utils.costumer.CostumersTemplate.NAME_MACERLA_SOUZA;
import static com.automobilefleet.utils.costumer.CostumersTemplate.NAME_RAIMUNDA_REGINA;

@ExtendWith(MockitoExtension.class)
class CostumerServiceTest {
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
        this.costumer = CostumerFactoryUtils.costumerBuildRegina();
        this.costumerResponse = CostumerFactoryUtils.costumerResponseBuildRegina();
        this.costumerRequest = CostumerFactoryUtils.costumerRequestBuildRegina();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldReturnAListWithOneElement() {
        final List<Costumer> costumerList = CostumerFactoryUtils.costumerListBuild();
        final List<CostumerResponse> costumerResponseList = CostumerFactoryUtils.costumerResponseListBuild();

        Mockito.when(this.costumerRepository.findAll()).thenReturn(costumerList);
        Mockito.when(this.mapper.map(costumerList.get(0), CostumerResponse.class)).thenReturn(costumerResponseList.get(0));
        Mockito.when(this.mapper.map(costumerList.get(1), CostumerResponse.class)).thenReturn(costumerResponseList.get(1));
        Mockito.when(this.mapper.map(costumerList.get(2), CostumerResponse.class)).thenReturn(costumerResponseList.get(2));

        final List<CostumerResponse> acutal = this.costumerService.listCostumer();

        // Assert list has data
        Assertions.assertNotNull(acutal);
        Assertions.assertFalse(acutal.isEmpty());
        Assertions.assertEquals(3, acutal.size());

        // Setup data for Strings check
        Map<String, List<String>> checkDataStrings =
                Map.of(
                        "Name", acutal.stream().map(CostumerResponse::getName).collect(Collectors.toList()),
                        "Email", acutal.stream().map(CostumerResponse::getEmail).collect(Collectors.toList()),
                        "License", acutal.stream().map(CostumerResponse::getDriverLicense).collect(Collectors.toList())
                );

        // Assert list of names costumers
        final List<String> listOfCostumerName = List.of(NAME_RAIMUNDA_REGINA, NAME_GUSTAVO_RAFAEL, NAME_MACERLA_SOUZA);
        Assertions.assertEquals(listOfCostumerName, checkDataStrings.get("Name"));

        // Assert list of emails costumers
        final List<String> listOfCostumerEmail = List.of(EMAIL_RAIMUNDA_REGINA, EMAIL_GUSTAVO_RAFAEL, EMAIL_MACERLA_SOUZA);
        Assertions.assertEquals(listOfCostumerEmail, checkDataStrings.get("Email"));

        // Assert list of driver's linceses costumers
        final List<String> listOfLicenses = List.of(DRIVER_LICENSE_RAIMUNDA_REGINA, DRIVER_LICENSE_GUSTAVO_RAFAEL, DRIVER_LICENSE_MACERLA_SOUZA);
        Assertions.assertEquals(listOfLicenses, checkDataStrings.get("License"));
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