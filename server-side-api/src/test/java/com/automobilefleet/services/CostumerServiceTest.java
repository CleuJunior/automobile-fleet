package com.automobilefleet.services;

import com.automobilefleet.api.request.CostumerRequest;
import com.automobilefleet.api.response.CostumerResponse;
import com.automobilefleet.entities.Costumer;
import com.automobilefleet.exceptions.ExceptionsConstants;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.repositories.CostumerRepository;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CostumerServiceTest {
    @InjectMocks
    private CostumerService costumerService;
    @Mock
    private CostumerRepository costumerRepository;
    @Mock
    private ModelMapper mapper;
    private Costumer costumer;
    private CostumerRequest costumerRequestMock;
    private CostumerResponse costumerResponseMock;
    private static final long ID = 1L;
    private static final long NON_EXISTING_ID = 99L;
    private static final String NAME = "John Doe";
    private static final LocalDate BIRTH_DATE = LocalDate.of(1988, 12, 30);
    private static final String EMAIL = "johndoe@outlook.com";
    private static final String DRIVE_LICENSE = "51530697720";
    private static final String ADDRESS = "Street test, 00";
    private static final String PHONE = "(98) 3789-7445";
    private static final LocalDateTime CREATED_AT = LocalDateTime.of(2022, 9, 11, 9, 31, 23);
    private static final LocalDateTime UPDATE_AT = LocalDateTime.of(2022, 9, 14, 9, 31, 23);

    @BeforeEach
    void setUp() {
        this.costumer = new Costumer(ID, NAME, BIRTH_DATE, EMAIL, DRIVE_LICENSE, ADDRESS, PHONE, CREATED_AT, UPDATE_AT);
        this.costumerResponseMock = new CostumerResponse(ID, NAME, BIRTH_DATE, EMAIL, DRIVE_LICENSE, ADDRESS, PHONE, CREATED_AT, UPDATE_AT);
        this.costumerRequestMock = new CostumerRequest(NAME, BIRTH_DATE, EMAIL, DRIVE_LICENSE, ADDRESS, PHONE);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldReturnAListWithOneElement() {
        Mockito.when(this.costumerRepository.findAll()).thenReturn(List.of(this.costumer));
        Mockito.when(this.mapper.map(this.costumer, CostumerResponse.class)).thenReturn(this.costumerResponseMock);

        List<CostumerResponse> expected =  this.costumerService.listCostumer();
        Assertions.assertNotNull(expected);

//        Assertions.assertDoesNotThrow(() -> new NotFoundException(ExceptionsConstants.COSTUMER_NOT_FOUND));

        Mockito.verify(this.costumerRepository).findAll();
        Mockito.verifyNoMoreInteractions(this.costumerRepository);
    }

    @Test
    void shouldReturnOneCostumerById() {
        Mockito.when(this.costumerRepository.findById(ID)).thenReturn(Optional.ofNullable(this.costumer));
        Mockito.when(this.mapper.map(this.costumer, CostumerResponse.class)).thenReturn(this.costumerResponseMock);

        CostumerResponse expected =  this.costumerService.getCostumerById(ID);
        Assertions.assertNotNull(expected);
        Assertions.assertDoesNotThrow(() -> new NotFoundException(ExceptionsConstants.COSTUMER_NOT_FOUND));
        Assertions.assertInstanceOf(CostumerResponse.class, expected);
        Assertions.assertEquals(expected, this.costumerResponseMock);

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
        Mockito.when(this.mapper.map(this.costumer, CostumerResponse.class)).thenReturn(this.costumerResponseMock);
        Mockito.when(this.mapper.map(this.costumerRequestMock, Costumer.class)).thenReturn(this.costumer);

        CostumerResponse expected = this.costumerService.saveCostumer(this.costumerRequestMock);

        Assertions.assertNotNull(expected);
        Assertions.assertInstanceOf(CostumerResponse.class, expected);
        Assertions.assertEquals(expected, this.costumerResponseMock);
    }

    @Test
    void shouldUpdateCostumerWhenCallingUpdate() {
        String nameUpdate = "Update Working";
        this.costumerRequestMock.setName(nameUpdate);
        this.costumerResponseMock.setName(nameUpdate);

        // Configurar o comportamento dos mocks
        Mockito.when(this.costumerRepository.findById(ID)).thenReturn(Optional.of(this.costumer));
        Mockito.when(this.mapper.map(this.costumer, CostumerResponse.class)).thenReturn(this.costumerResponseMock);

        // Chamar o método a ser testado
        CostumerResponse result = this.costumerService.updateCostumer(ID, this.costumerRequestMock);

        // Verificar o resultado
        Assertions.assertNotNull(result);
        Assertions.assertEquals(this.costumerResponseMock.getId(), result.getId());
        Assertions.assertEquals(this.costumerResponseMock.getName(), result.getName());
        Assertions.assertSame(this.costumerResponseMock, result);

        // Verificar as interações dos mocks
        Mockito.verify(this.costumerRepository).findById(ID);
        Mockito.verify(costumerRepository).save(this.costumer);
        Mockito.verify(this.mapper).map(this.costumer, CostumerResponse.class);
        Mockito.verifyNoMoreInteractions(this.costumerRepository, this.mapper);
    }
}