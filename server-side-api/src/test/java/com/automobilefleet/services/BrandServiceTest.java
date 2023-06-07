//package com.automobilefleet.services;
//
//import com.automobilefleet.api.reponse.BrandResponse;
//import com.automobilefleet.api.request.BrandRequest;
//import com.automobilefleet.entities.Brand;
//import com.automobilefleet.exceptions.BrandNotFoundException;
//import com.automobilefleet.repositories.BrandRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.modelmapper.ModelMapper;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//@ExtendWith(SpringExtension.class)
//class BrandServiceTest {
//
//    @InjectMocks
//    private BrandService brandService;
//
//    @Mock
//    private BrandRepository brandRepository;
//
//    @Mock
//    private ModelMapper mapper;
//
//    private Brand brandUnderTest;
//
//    private static final Long EXISTING_ID = 1L;
//
//    private static final Long NON_EXISTING_ID = 2147483647L;
//
//
//    @BeforeEach
//    void setUp() {
//        brandUnderTest = new Brand(EXISTING_ID, "Brand Under Test");
//    }
//
//    @Test
//    void shouldReturnListOfBrandsWhenCallingFindAll() {
//        List<Brand> brands = Arrays.asList(
//                new Brand(1L, "Brand 1"),
//                new Brand(2L, "Brand 2"),
//                new Brand(3L, "Brand 3")
//        );
//
//        Mockito.when(this.brandRepository.findAll()).thenReturn(brands);
//
//        List<BrandResponse> expectedResponses = new ArrayList<>();
//        expectedResponses.add(new BrandResponse(1L, "Brand 1"));
//        expectedResponses.add(new BrandResponse(2L, "Brand 2"));
//        expectedResponses.add(new BrandResponse(3L, "Brand 3"));
//
//        brands.forEach(
//                b -> Mockito.when(this.mapper.map(b, BrandResponse.class))
//                        .thenReturn(expectedResponses.get(brands.indexOf(b)))
//        );
//
//        List<BrandResponse> actualResponses = this.brandService.listBrand();
//        Assertions.assertEquals(expectedResponses, actualResponses);
//    }
//
//    @Test
//    void shouldReturnABrandWhenCallingGetBrand() {
//        Mockito.when(this.brandRepository.findById(1L)).thenReturn(Optional.of(this.brandUnderTest));
//
//        BrandResponse expected = new BrandResponse(1L, "Brand 1");
//        Mockito.when(this.mapper.map(this.brandUnderTest, BrandResponse.class)).thenReturn(expected);
//
//        BrandResponse actual = this.brandService.getBrand(EXISTING_ID);
//        Assertions.assertEquals(expected, actual);
//    }
//
//    @Test
//    void shouldThrowBrandNotFoundExceptionWhenCallingGetBrand() {
//        Mockito.when(this.brandRepository.findById(NON_EXISTING_ID)).thenReturn(Optional.empty());
//
//        Assertions.assertThrows(BrandNotFoundException.class, () -> this.brandService.getBrand(NON_EXISTING_ID));
//
//        Mockito.verify(this.brandRepository, Mockito.times(1)).findById(NON_EXISTING_ID);
//    }
//
//    @Test
//    void shouldReturnBrandWhenCallingSaveBrand() {
//        BrandRequest request = new BrandRequest("Brand 1");
//
//        Mockito.when(mapper.map(request, Brand.class)).thenReturn(this.brandUnderTest);
//
//        Mockito.when(brandRepository.save(this.brandUnderTest)).thenReturn(this.brandUnderTest);
//
//        BrandResponse expectedResponse = new BrandResponse(1L, "Brand 1");
//        Mockito.when(mapper.map(this.brandUnderTest, BrandResponse.class)).thenReturn(expectedResponse);
//
//        BrandResponse actualResponse = brandService.saveBrand(request);
//
//        Assertions.assertEquals(expectedResponse, actualResponse);
//        Mockito.verify(this.mapper).map(request, Brand.class);
//        Mockito.verify(this.brandRepository).save(this.brandUnderTest);
//        Mockito.verify(this.mapper).map(this.brandUnderTest, BrandResponse.class);
//    }
//
//    @Test
//    void shouldUpdateExistingBrandWhenCallingUpdateBrand() {
//        BrandRequest request = new BrandRequest("Updated Brand");
//
//        Mockito.when(this.brandRepository.findById(EXISTING_ID)).thenReturn(Optional.of(this.brandUnderTest));
//
//        Brand updatedBrand = new Brand(EXISTING_ID, "Updated Brand");
//        Mockito.when(this.mapper.map(request, Brand.class)).thenReturn(updatedBrand);
//
//        BrandResponse expectedResponse = new BrandResponse(EXISTING_ID, "Updated Brand");
//        Mockito.when(this.mapper.map(updatedBrand, BrandResponse.class)).thenReturn(expectedResponse);
//
//        BrandResponse actualResponse = this.brandService.updateBrand(EXISTING_ID, request);
//
//        Mockito.verify(this.brandRepository).findById(EXISTING_ID);
//        Mockito.verify(this.mapper).map(updatedBrand, BrandResponse.class);
//        Mockito.verify(this.brandRepository).save(updatedBrand);
//
//        Assertions.assertEquals(expectedResponse, actualResponse);
//    }
//}
