package com.automobilefleet.utils;

import com.automobilefleet.api.request.CategoryRequest;
import com.automobilefleet.api.request.CostumerRequest;
import com.automobilefleet.api.response.CategoryResponse;
import com.automobilefleet.api.response.CostumerResponse;
import com.automobilefleet.entities.Brand;
import com.automobilefleet.entities.Category;
import com.automobilefleet.entities.Costumer;
import lombok.experimental.UtilityClass;

import static com.automobilefleet.constant.ExpectedAttributesConstant.ADDRESS_COSTUMER;
import static com.automobilefleet.constant.ExpectedAttributesConstant.BIRTHDATE_COSTUMERA;
import static com.automobilefleet.constant.ExpectedAttributesConstant.EXPECTED_CREATED_AT_BRAND;
import static com.automobilefleet.constant.ExpectedAttributesConstant.CREATED_AT_CATEGORY;
import static com.automobilefleet.constant.ExpectedAttributesConstant.CREATED_AT_COSTUMER;
import static com.automobilefleet.constant.ExpectedAttributesConstant.DESCRIPTION_CATEGORY;
import static com.automobilefleet.constant.ExpectedAttributesConstant.DRIVER_LICENSE_COSTUMER;
import static com.automobilefleet.constant.ExpectedAttributesConstant.EMAIL_COSTUMER;
import static com.automobilefleet.constant.ExpectedAttributesConstant.EXPECTED_ID_BRAND;
import static com.automobilefleet.constant.ExpectedAttributesConstant.ID_CATEGORY;
import static com.automobilefleet.constant.ExpectedAttributesConstant.ID_COSTUMER;
import static com.automobilefleet.constant.ExpectedAttributesConstant.EXPECTED_NAME_BRAND;
import static com.automobilefleet.constant.ExpectedAttributesConstant.NAME_CATEGORY;
import static com.automobilefleet.constant.ExpectedAttributesConstant.NAME_COSTUMER;
import static com.automobilefleet.constant.ExpectedAttributesConstant.PHONE_COSTUMER;
import static com.automobilefleet.constant.ExpectedAttributesConstant.UPDATED_AT_COSTUMER;


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
        return new Category(ID_CATEGORY, NAME_CATEGORY, DESCRIPTION_CATEGORY, CREATED_AT_CATEGORY);
    }

    public static CategoryResponse createCategoryResponse() {
        return new CategoryResponse(ID_CATEGORY, NAME_CATEGORY, DESCRIPTION_CATEGORY, CREATED_AT_CATEGORY);
    }

    public static CategoryRequest createCategoryRequest() {
        return new CategoryRequest(NAME_CATEGORY, DESCRIPTION_CATEGORY, CREATED_AT_CATEGORY);
    }

    public static Brand createBrand() {
        return Brand.builder()
                .id(EXPECTED_ID_BRAND)
                .name(EXPECTED_NAME_BRAND)
                .createdAt(EXPECTED_CREATED_AT_BRAND)
                .build();
    }
}