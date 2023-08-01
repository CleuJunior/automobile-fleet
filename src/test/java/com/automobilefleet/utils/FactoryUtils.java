package com.automobilefleet.utils;

import com.automobilefleet.api.dto.request.BrandRequest;
import com.automobilefleet.api.dto.request.CategoryRequest;
import com.automobilefleet.api.dto.request.CostumerRequest;
import com.automobilefleet.api.dto.response.BrandResponse;
import com.automobilefleet.api.dto.response.CategoryResponse;
import com.automobilefleet.api.dto.response.CostumerResponse;
import com.automobilefleet.api.dto.response.SpecificationResponse;
import com.automobilefleet.entities.Brand;
import com.automobilefleet.entities.Category;
import com.automobilefleet.entities.Costumer;
import com.automobilefleet.entities.Specification;
import lombok.experimental.UtilityClass;

import static com.automobilefleet.utils.constant.ExpectedAttributesConstant.*;


@UtilityClass
public class FactoryUtils {

    public static Costumer createCostumer() {
        return Costumer.builder()
                .id(ID_COSTUMER)
                .name(NAME_COSTUMER)
                .birthdate(BIRTHDATE_COSTUMERA)
                .email(EMAIL_COSTUMER)
                .driverLicense(DRIVER_LICENSE_COSTUMER)
                .address(ADDRESS_COSTUMER)
                .phone(PHONE_COSTUMER)
                .createdAt(CREATED_AT_COSTUMER)
                .updatedAt(UPDATED_AT_COSTUMER)
                .build();
    }

    public static CostumerResponse createCostumerResponse() {
        return CostumerResponse.builder()
                .id(ID_COSTUMER)
                .name(NAME_COSTUMER)
                .birthdate(BIRTHDATE_COSTUMERA)
                .email(EMAIL_COSTUMER)
                .driverLicense(DRIVER_LICENSE_COSTUMER)
                .address(ADDRESS_COSTUMER)
                .phone(PHONE_COSTUMER)
                .createdAt(CREATED_AT_COSTUMER)
                .updatedAt(UPDATED_AT_COSTUMER)
                .build();
    }

    public static CostumerRequest createCostumerRequest() {
        return CostumerRequest.builder()
                .name(NAME_COSTUMER)
                .birthdate(BIRTHDATE_COSTUMERA)
                .email(EMAIL_COSTUMER)
                .driverLicense(DRIVER_LICENSE_COSTUMER)
                .address(ADDRESS_COSTUMER)
                .phone(PHONE_COSTUMER)
                .build();
    }

    public static Category createCategory() {
        return Category.builder()
                .id(ID_CATEGORY)
                .name(NAME_CATEGORY)
                .description(DESCRIPTION_CATEGORY)
                .createdAt(CREATED_AT_CATEGORY)
                .build();
    }

    public static CategoryResponse createCategoryResponse() {
        return new CategoryResponse(ID_CATEGORY, NAME_CATEGORY, DESCRIPTION_CATEGORY, CREATED_AT_CATEGORY);
    }

    public static CategoryRequest createCategoryRequest() {
        return new CategoryRequest(NAME_CATEGORY, DESCRIPTION_CATEGORY);
    }

    public static Brand createBrand() {
        return Brand.builder()
                .id(EXPECTED_ID_BRAND)
                .name(EXPECTED_NAME_BRAND)
                .createdAt(EXPECTED_CREATED_AT_BRAND)
                .build();
    }

    public static BrandResponse createBrandReponse() {
        return new BrandResponse(EXPECTED_ID_BRAND, EXPECTED_NAME_BRAND, EXPECTED_CREATED_AT_BRAND);
    }

    public static BrandRequest createBrandRequest() {
        return new BrandRequest(EXPECTED_NAME_BRAND);
    }

    public static Specification createSpecification() {
        return Specification
                .builder()
                .id(EXPECTED_ID_SPECIFICATION)
                .name(EXPECTED_NAME_SPECIFICATION)
                .description(EXPECTED_DESCRIPTION_SPECIFICATION)
                .createdAt(EXPECTED_CREATED_AT_SPECIFICATION)
                .build();
    }

    public static SpecificationResponse createSpecificationReponse() {
        return new SpecificationResponse(EXPECTED_ID_SPECIFICATION, EXPECTED_NAME_SPECIFICATION,
                EXPECTED_DESCRIPTION_SPECIFICATION, EXPECTED_CREATED_AT_SPECIFICATION);
    }
}