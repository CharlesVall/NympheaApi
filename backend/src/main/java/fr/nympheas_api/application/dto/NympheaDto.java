package fr.nympheas_api.application.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonPropertyOrder({"id", "title", "date", "dimensions", "location", "country", "statut", "localFile"})
public class NympheaDto {
    Long id;
    String title;
    String date;
    String dimensions;
    String location;
    String country;
    String statut;
    String localFile;
}