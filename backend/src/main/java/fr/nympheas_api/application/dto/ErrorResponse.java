package fr.nympheas_api.application.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Value;

import java.time.Instant;

@Value
@Builder
@JsonPropertyOrder({"timestamp", "status", "error", "message", "path"})
public class ErrorResponse {

    Instant timestamp;
    int status;
    String error;
    String message;
    String path;
}