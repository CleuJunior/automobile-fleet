package com.automobilefleet.utils.costumer;

import com.automobilefleet.api.request.CostumerRequest;
import com.automobilefleet.api.response.CostumerResponse;
import com.automobilefleet.entities.Costumer;
import lombok.experimental.UtilityClass;

import java.util.List;


@UtilityClass
public class CostumerFactoryUtils {

    public static Costumer costumerBuildRegina() {
        return new Costumer(
                CostumersTemplate.ID_RAIMUNDA_REGINA,
                CostumersTemplate.NAME_RAIMUNDA_REGINA,
                CostumersTemplate.BIRTH_DATE_RAIMUNDA_REGINA,
                CostumersTemplate.EMAIL_RAIMUNDA_REGINA,
                CostumersTemplate.DRIVER_LICENSE_RAIMUNDA_REGINA,
                CostumersTemplate.ADDRESS_RAIMUNDA_REGINA,
                CostumersTemplate.PHONE_RAIMUNDA_REGINA,
                CostumersTemplate.CREATED_AT_RAIMUNDA_REGINA,
                CostumersTemplate.UPDATED_AT_RAIMUNDA_REGINA
        );
    }

    public static CostumerResponse costumerResponseBuildRegina() {
        return new CostumerResponse(
                CostumersTemplate.ID_RAIMUNDA_REGINA,
                CostumersTemplate.NAME_RAIMUNDA_REGINA,
                CostumersTemplate.BIRTH_DATE_RAIMUNDA_REGINA,
                CostumersTemplate.EMAIL_RAIMUNDA_REGINA,
                CostumersTemplate.DRIVER_LICENSE_RAIMUNDA_REGINA,
                CostumersTemplate.ADDRESS_RAIMUNDA_REGINA,
                CostumersTemplate.PHONE_RAIMUNDA_REGINA,
                CostumersTemplate.CREATED_AT_RAIMUNDA_REGINA,
                CostumersTemplate.UPDATED_AT_RAIMUNDA_REGINA
        );
    }

    public static CostumerRequest costumerRequestBuildRegina() {
        return new CostumerRequest(
                CostumersTemplate.NAME_RAIMUNDA_REGINA,
                CostumersTemplate.BIRTH_DATE_RAIMUNDA_REGINA,
                CostumersTemplate.EMAIL_RAIMUNDA_REGINA,
                CostumersTemplate.DRIVER_LICENSE_RAIMUNDA_REGINA,
                CostumersTemplate.ADDRESS_RAIMUNDA_REGINA,
                CostumersTemplate.PHONE_RAIMUNDA_REGINA
        );
    }

    public static Costumer costumerBuildGustavo() {
        return new Costumer(
                CostumersTemplate.ID_GUSTAVO_RAFAEL,
                CostumersTemplate.NAME_GUSTAVO_RAFAEL,
                CostumersTemplate.BIRTH_DATE_GUSTAVO_RAFAEL,
                CostumersTemplate.EMAIL_GUSTAVO_RAFAEL,
                CostumersTemplate.DRIVER_LICENSE_GUSTAVO_RAFAEL,
                CostumersTemplate.ADDRESS_GUSTAVO_RAFAEL,
                CostumersTemplate.PHONE_GUSTAVO_RAFAEL,
                CostumersTemplate.CREATED_AT_GUSTAVO_RAFAEL,
                CostumersTemplate.UPDATED_AT_GUSTAVO_RAFAEL
        );
    }

    public static CostumerResponse costumerResponseBuildGustavo() {
        return new CostumerResponse(
                CostumersTemplate.ID_GUSTAVO_RAFAEL,
                CostumersTemplate.NAME_GUSTAVO_RAFAEL,
                CostumersTemplate.BIRTH_DATE_GUSTAVO_RAFAEL,
                CostumersTemplate.EMAIL_GUSTAVO_RAFAEL,
                CostumersTemplate.DRIVER_LICENSE_GUSTAVO_RAFAEL,
                CostumersTemplate.ADDRESS_GUSTAVO_RAFAEL,
                CostumersTemplate.PHONE_GUSTAVO_RAFAEL,
                CostumersTemplate.CREATED_AT_GUSTAVO_RAFAEL,
                CostumersTemplate.UPDATED_AT_GUSTAVO_RAFAEL
        );
    }

    public static Costumer costumerBuildMaercela() {
        return new Costumer(
                CostumersTemplate.ID_MACERLA_SOUZA,
                CostumersTemplate.NAME_MACERLA_SOUZA,
                CostumersTemplate.BIRTH_DATE_MACERLA_SOUZA,
                CostumersTemplate.EMAIL_MACERLA_SOUZA,
                CostumersTemplate.DRIVER_LICENSE_MACERLA_SOUZA,
                CostumersTemplate.ADDRESS_MACERLA_SOUZA,
                CostumersTemplate.PHONE_MACERLA_SOUZA,
                CostumersTemplate.CREATED_AT_MACERLA_SOUZA,
                CostumersTemplate.UPDATED_AT_MACERLA_SOUZA
        );
    }

    public static CostumerResponse costumerReponseBuildMaercela() {
        return new CostumerResponse(
                CostumersTemplate.ID_MACERLA_SOUZA,
                CostumersTemplate.NAME_MACERLA_SOUZA,
                CostumersTemplate.BIRTH_DATE_MACERLA_SOUZA,
                CostumersTemplate.EMAIL_MACERLA_SOUZA,
                CostumersTemplate.DRIVER_LICENSE_MACERLA_SOUZA,
                CostumersTemplate.ADDRESS_MACERLA_SOUZA,
                CostumersTemplate.PHONE_MACERLA_SOUZA,
                CostumersTemplate.CREATED_AT_MACERLA_SOUZA,
                CostumersTemplate.UPDATED_AT_MACERLA_SOUZA
        );
    }

    public static List<Costumer> costumerListBuild() {
        return List.of(costumerBuildRegina(), costumerBuildGustavo(), costumerBuildMaercela());
    }

    public static List<CostumerResponse> costumerResponseListBuild() {
        return List.of(costumerResponseBuildRegina(), costumerResponseBuildGustavo(), costumerReponseBuildMaercela());
    }
}