package com.automobilefleet.api.reponse;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@JsonPropertyOrder({"_id"})
public class RentalResponse {

    @JsonProperty("_id")
    private Long id;

    @JsonProperty("car_id")
    private Long carId;

    @JsonProperty("costumer_id")
    private Long costumerId;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy")
    @JsonProperty("start_date")
    private LocalDate startDate;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy")
    @JsonProperty("end_date")
    private LocalDate endDate;

    @JsonProperty("total")
    private Double total;

    @JsonProperty("created_at")
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime createdAt;

    @JsonProperty("update_at")
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime updateAt;
}
