package com.automobilefleet.utils;

import com.automobilefleet.api.request.CategoryRequest;
import com.automobilefleet.api.request.CostumerRequest;
import com.automobilefleet.api.response.CategoryResponse;
import com.automobilefleet.api.response.CostumerResponse;
import com.automobilefleet.entities.Category;
import com.automobilefleet.entities.Costumer;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.automobilefleet.utils.EntitiesTemplate.ADDRESS_RAIMUNDA_REGINA;
import static com.automobilefleet.utils.EntitiesTemplate.BIRTHDATE_RAIMUNDA_REGINA;
import static com.automobilefleet.utils.EntitiesTemplate.CREATED_AT_CATEGORY;
import static com.automobilefleet.utils.EntitiesTemplate.CREATED_AT_RAIMUNDA_REGINA;
import static com.automobilefleet.utils.EntitiesTemplate.DESCRIPTION_CATEGORY;
import static com.automobilefleet.utils.EntitiesTemplate.DRIVER_LICENSE_RAIMUNDA_REGINA;
import static com.automobilefleet.utils.EntitiesTemplate.EMAIL_RAIMUNDA_REGINA;
import static com.automobilefleet.utils.EntitiesTemplate.ID_CATEGORY;
import static com.automobilefleet.utils.EntitiesTemplate.ID_RAIMUNDA_REGINA;
import static com.automobilefleet.utils.EntitiesTemplate.NAME_CATEGORY;
import static com.automobilefleet.utils.EntitiesTemplate.NAME_RAIMUNDA_REGINA;
import static com.automobilefleet.utils.EntitiesTemplate.PHONE_RAIMUNDA_REGINA;
import static com.automobilefleet.utils.EntitiesTemplate.UPDATED_AT_RAIMUNDA_REGINA;


@UtilityClass
public class FactoryUtils {

    public static Costumer createCostumer() {
        return Costumer.builder()
                .id(ID_RAIMUNDA_REGINA)
                .name(NAME_RAIMUNDA_REGINA)
                .birthDate(BIRTHDATE_RAIMUNDA_REGINA)
                .email(EMAIL_RAIMUNDA_REGINA)
                .driverLicense(DRIVER_LICENSE_RAIMUNDA_REGINA)
                .address(ADDRESS_RAIMUNDA_REGINA)
                .phone(PHONE_RAIMUNDA_REGINA)
                .createdAt(CREATED_AT_RAIMUNDA_REGINA)
                .updatedAt(UPDATED_AT_RAIMUNDA_REGINA)
                .build();
    }

    public static CostumerResponse createCostumerResponse() {
        return CostumerResponse.builder()
                .id(ID_RAIMUNDA_REGINA)
                .name(NAME_RAIMUNDA_REGINA)
                .birthDate(BIRTHDATE_RAIMUNDA_REGINA)
                .email(EMAIL_RAIMUNDA_REGINA)
                .driverLicense(DRIVER_LICENSE_RAIMUNDA_REGINA)
                .address(ADDRESS_RAIMUNDA_REGINA)
                .phone(PHONE_RAIMUNDA_REGINA)
                .createdAt(CREATED_AT_RAIMUNDA_REGINA)
                .updatedAt(UPDATED_AT_RAIMUNDA_REGINA)
                .build();
    }

    public static CostumerRequest createCostumerRequest() {
        return CostumerRequest.builder()
                .name(NAME_RAIMUNDA_REGINA)
                .birthdate(BIRTHDATE_RAIMUNDA_REGINA)
                .email(EMAIL_RAIMUNDA_REGINA)
                .driverLicense(DRIVER_LICENSE_RAIMUNDA_REGINA)
                .address(ADDRESS_RAIMUNDA_REGINA)
                .phone(PHONE_RAIMUNDA_REGINA)
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

}

class EntitiesTemplate {

    // COSTUMER 01
    public static final UUID ID_RAIMUNDA_REGINA = UUID.fromString("32ca0461-0401-4b15-bf57-3d2b18b3828f");
    public static final String NAME_RAIMUNDA_REGINA = "Raimunda Regina Porto";
    public static final LocalDate BIRTHDATE_RAIMUNDA_REGINA = LocalDate.of(1974, 3, 5);
    public static final String EMAIL_RAIMUNDA_REGINA = "raimundareginaporto@clinicasilhouette.com.br";
    public static final String DRIVER_LICENSE_RAIMUNDA_REGINA = "08493447718";
    public static final String ADDRESS_RAIMUNDA_REGINA = "Rua Astrogildo de Almeida, 328";
    public static final String PHONE_RAIMUNDA_REGINA = "(73) 99362-1339";
    public static final LocalDateTime CREATED_AT_RAIMUNDA_REGINA = LocalDateTime.of(2021, 8, 2, 8, 22, 1);
    public static final LocalDateTime UPDATED_AT_RAIMUNDA_REGINA = LocalDateTime.of(2022, 12, 24, 21, 29, 21);

    // CATEGORY
    public static final UUID ID_CATEGORY = UUID.fromString("b86a92d8-6908-426e-8316-f72b0c849a4b");
    public static final String NAME_CATEGORY = "SUVs";
    public static final String DESCRIPTION_CATEGORY = "Veículos utilitários esportivos";
    public static final LocalDateTime CREATED_AT_CATEGORY = LocalDateTime.of(2019, 12, 23, 8, 22, 11);
}