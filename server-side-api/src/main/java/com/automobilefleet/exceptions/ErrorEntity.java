package com.automobilefleet.exceptions;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonPropertyOrder({"status", "message", "timestamp"})
public class ErrorEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private Instant timestamp;
    private Integer status;
    private String message;
}
