package com.automobilefleet.api.dto.response;

import com.automobilefleet.entities.Costumer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@JsonPropertyOrder({"_id", "name", "birth_date", "email", "driver_license", "address", "phone_number"})
public record CostumerResponse(
        @JsonProperty("_id") UUID id,

        String name,

        @JsonProperty("birth_date")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy") LocalDate birthdate,
        String email,

        @JsonProperty("driver_license") String driverLicense,

        String address,

        @JsonProperty("phone_number") String phone
) {

    public CostumerResponse(Costumer costumer) {
        this(
                costumer.getId(),
                costumer.getName(),
                costumer.getBirthdate(),
                costumer.getEmail(),
                costumer.getDriverLicense(),
                costumer.getAddress(),
                costumer.getPhone()
        );
    }
}
