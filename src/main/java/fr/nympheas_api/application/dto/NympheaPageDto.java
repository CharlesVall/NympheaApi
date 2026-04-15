package fr.nympheas_api.application.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
@JsonPropertyOrder({"page","size","totalElements","totalPages","content"})
public class NympheaPageDto {

    int page;
    int size;
    long totalElements;
    int totalPages;
    List<NympheaDto> content;
}