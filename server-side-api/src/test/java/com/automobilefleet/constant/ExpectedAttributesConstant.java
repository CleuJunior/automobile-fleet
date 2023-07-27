package com.automobilefleet.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExpectedAttributesConstant {

    // COSTUMER
    public static final UUID ID_COSTUMER = UUID.fromString("32ca0461-0401-4b15-bf57-3d2b18b3828f");
    public static final String NAME_COSTUMER = "Raimunda Regina Porto";
    public static final LocalDate BIRTHDATE_COSTUMERA = LocalDate.of(1974, 3, 5);
    public static final String EMAIL_COSTUMER = "raimundareginaporto@clinicasilhouette.com.br";
    public static final String DRIVER_LICENSE_COSTUMER = "08493447718";
    public static final String ADDRESS_COSTUMER = "Rua Astrogildo de Almeida, 328";
    public static final String PHONE_COSTUMER = "(73) 99362-1339";
    public static final LocalDateTime CREATED_AT_COSTUMER = LocalDateTime.of(2021, 8, 2, 8, 22, 1);
    public static final LocalDateTime UPDATED_AT_COSTUMER = LocalDateTime.of(2022, 12, 24, 21, 29, 21);

    // CATEGORY
    public static final UUID ID_CATEGORY = UUID.fromString("b86a92d8-6908-426e-8316-f72b0c849a4b");
    public static final String NAME_CATEGORY = "SUVs";
    public static final String DESCRIPTION_CATEGORY = "Veículos utilitários esportivos";
    public static final LocalDateTime CREATED_AT_CATEGORY = LocalDateTime.of(2019, 12, 23, 8, 22, 11);

    // BRAND
    public static final UUID EXPECTED_ID_BRAND = UUID.fromString("0a7d6250-0be5-4036-8f23-33dc1762bed0");
    public static final String EXPECTED_NAME_BRAND = "BMW";
    public static final LocalDateTime EXPECTED_CREATED_AT_BRAND = LocalDateTime.of(2018, 7, 30, 12, 33, 33);

}
