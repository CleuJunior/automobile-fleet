package com.automobilefleet.api.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CustomerRequest(
        @NotBlank(message = "Name can't be blank!")
        @Size(min = 2, max = 255, message = "Name must contain between 2 to 255 characters!")
        String name,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
        @Past(message = "Entered date is incorrect!")
        LocalDate birthdate,
        @Email(message = "Must be a well-formed email address!")
        String email,
        @JsonProperty("driver_license")
        @NotBlank(message = "Driver's license can't be blank!")
        @Size(min = 11, max = 11, message = "Driver's license length must be 11!")
        String driverLicense,
        @NotBlank(message = "Address can't be blank!")
        @Size(min = 8, max = 255, message = "Address must contain between 8 to 255 characters!")
        String address,
        @JsonProperty("phone_number")
        @Pattern(regexp= "^\\(?\\d{2}\\)?[\\s-]?[\\s9]?\\d{5}-?\\d{4}$", message= "Phone format must be (12) 12345-1234")
        String phone
) { }