package com.automobilefleet.exceptions.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonPropertyOrder({"timestamp", "status", "status_message", "error_message"})
public class MultiplesErrorsResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer status;
    @JsonProperty("status_message")
    private String statusErrorMessage;
    @JsonProperty("errors_message")
    private List<String> messages;
    @JsonFormat(shape = STRING, pattern = "dd-MM-yyyy HH:mm:SS")
    private LocalDateTime timestamp;
}
