package com.automobilefleet.search;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerSearch extends PageSearch {
    private String name;
    private String email;
    @JsonProperty("driver_license")
    private String driverLicense;
    private String address;
    @JsonProperty("phone_number")
    private String phone;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthdate;

}
