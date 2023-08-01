package com.automobilefleet.exceptions.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonPropertyOrder({"timestamp", "status", "status_message", "error_message"})
public class MultiplesErrorsResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer status;
    @JsonProperty("status_message")
    private String statusErrorMessage;
    @JsonProperty("error_message")
    private String messages;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:SS")
    private LocalDateTime timestamp;
}
