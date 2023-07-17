package com.automobilefleet.utils.costumer;

import com.automobilefleet.api.request.CostumerRequest;
import com.automobilefleet.api.response.CostumerResponse;
import com.automobilefleet.entities.Costumer;
import lombok.experimental.UtilityClass;

import java.util.List;

import static com.automobilefleet.utils.costumer.CostumersTemplate.ADDRESS_GUSTAVO_RAFAEL;
import static com.automobilefleet.utils.costumer.CostumersTemplate.ADDRESS_MACERLA_SOUZA;
import static com.automobilefleet.utils.costumer.CostumersTemplate.ADDRESS_RAIMUNDA_REGINA;
import static com.automobilefleet.utils.costumer.CostumersTemplate.BIRTHDATE_GUSTAVO_RAFAEL;
import static com.automobilefleet.utils.costumer.CostumersTemplate.BIRTHDATE_MACERLA_SOUZA;
import static com.automobilefleet.utils.costumer.CostumersTemplate.BIRTHDATE_RAIMUNDA_REGINA;
import static com.automobilefleet.utils.costumer.CostumersTemplate.CREATED_AT_GUSTAVO_RAFAEL;
import static com.automobilefleet.utils.costumer.CostumersTemplate.CREATED_AT_MACERLA_SOUZA;
import static com.automobilefleet.utils.costumer.CostumersTemplate.CREATED_AT_RAIMUNDA_REGINA;
import static com.automobilefleet.utils.costumer.CostumersTemplate.DRIVER_LICENSE_GUSTAVO_RAFAEL;
import static com.automobilefleet.utils.costumer.CostumersTemplate.DRIVER_LICENSE_MACERLA_SOUZA;
import static com.automobilefleet.utils.costumer.CostumersTemplate.DRIVER_LICENSE_RAIMUNDA_REGINA;
import static com.automobilefleet.utils.costumer.CostumersTemplate.EMAIL_GUSTAVO_RAFAEL;
import static com.automobilefleet.utils.costumer.CostumersTemplate.EMAIL_MACERLA_SOUZA;
import static com.automobilefleet.utils.costumer.CostumersTemplate.EMAIL_RAIMUNDA_REGINA;
import static com.automobilefleet.utils.costumer.CostumersTemplate.ID_GUSTAVO_RAFAEL;
import static com.automobilefleet.utils.costumer.CostumersTemplate.ID_MACERLA_SOUZA;
import static com.automobilefleet.utils.costumer.CostumersTemplate.ID_RAIMUNDA_REGINA;
import static com.automobilefleet.utils.costumer.CostumersTemplate.NAME_GUSTAVO_RAFAEL;
import static com.automobilefleet.utils.costumer.CostumersTemplate.NAME_MACERLA_SOUZA;
import static com.automobilefleet.utils.costumer.CostumersTemplate.NAME_RAIMUNDA_REGINA;
import static com.automobilefleet.utils.costumer.CostumersTemplate.PHONE_GUSTAVO_RAFAEL;
import static com.automobilefleet.utils.costumer.CostumersTemplate.PHONE_MACERLA_SOUZA;
import static com.automobilefleet.utils.costumer.CostumersTemplate.PHONE_RAIMUNDA_REGINA;
import static com.automobilefleet.utils.costumer.CostumersTemplate.UPDATED_AT_GUSTAVO_RAFAEL;
import static com.automobilefleet.utils.costumer.CostumersTemplate.UPDATED_AT_MACERLA_SOUZA;
import static com.automobilefleet.utils.costumer.CostumersTemplate.UPDATED_AT_RAIMUNDA_REGINA;


@UtilityClass
public class CostumerFactoryUtils {

    public static Costumer costumerBuilRaimunda() {
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

    public static CostumerResponse costumerResponseBuildRaimunda() {
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

    public static CostumerRequest costumerRequestBuildRaimunda() {
        return CostumerRequest.builder()
                .name(NAME_RAIMUNDA_REGINA)
                .birthdate(BIRTHDATE_RAIMUNDA_REGINA)
                .email(EMAIL_RAIMUNDA_REGINA)
                .driverLicense(DRIVER_LICENSE_RAIMUNDA_REGINA)
                .address(ADDRESS_RAIMUNDA_REGINA)
                .phone(PHONE_RAIMUNDA_REGINA)
                .build();
    }

    public static Costumer costumerBuildGustavo() {
        return Costumer.builder()
                .id(ID_GUSTAVO_RAFAEL)
                .name(NAME_GUSTAVO_RAFAEL)
                .birthDate(BIRTHDATE_GUSTAVO_RAFAEL)
                .email(EMAIL_GUSTAVO_RAFAEL)
                .driverLicense(DRIVER_LICENSE_GUSTAVO_RAFAEL)
                .address(ADDRESS_GUSTAVO_RAFAEL)
                .phone(PHONE_GUSTAVO_RAFAEL)
                .createdAt(CREATED_AT_GUSTAVO_RAFAEL)
                .updatedAt(UPDATED_AT_GUSTAVO_RAFAEL)
                .build();
    }

    public static CostumerResponse costumerResponseBuildGustavo() {
        return CostumerResponse.builder()
                .id(ID_GUSTAVO_RAFAEL)
                .name(NAME_GUSTAVO_RAFAEL)
                .birthDate(BIRTHDATE_GUSTAVO_RAFAEL)
                .email(EMAIL_GUSTAVO_RAFAEL)
                .driverLicense(DRIVER_LICENSE_GUSTAVO_RAFAEL)
                .address(ADDRESS_GUSTAVO_RAFAEL)
                .phone(PHONE_GUSTAVO_RAFAEL)
                .createdAt(CREATED_AT_GUSTAVO_RAFAEL)
                .updatedAt(UPDATED_AT_GUSTAVO_RAFAEL)
                .build();
    }

    public static Costumer costumerBuildMaercela() {
        return Costumer.builder()
                .id(ID_MACERLA_SOUZA)
                .name(NAME_MACERLA_SOUZA)
                .birthDate(BIRTHDATE_MACERLA_SOUZA)
                .email(EMAIL_MACERLA_SOUZA)
                .driverLicense(DRIVER_LICENSE_MACERLA_SOUZA)
                .address(ADDRESS_MACERLA_SOUZA)
                .phone(PHONE_MACERLA_SOUZA)
                .createdAt(CREATED_AT_MACERLA_SOUZA)
                .updatedAt(UPDATED_AT_MACERLA_SOUZA)
                .build();
    }

    public static CostumerResponse costumerReponseBuildMaercela() {
        return CostumerResponse.builder()
                .id(ID_MACERLA_SOUZA)
                .name(NAME_MACERLA_SOUZA)
                .birthDate(BIRTHDATE_MACERLA_SOUZA)
                .email(EMAIL_MACERLA_SOUZA)
                .driverLicense(DRIVER_LICENSE_MACERLA_SOUZA)
                .address(ADDRESS_MACERLA_SOUZA)
                .phone(PHONE_MACERLA_SOUZA)
                .createdAt(CREATED_AT_MACERLA_SOUZA)
                .updatedAt(UPDATED_AT_MACERLA_SOUZA)
                .build();
    }

}