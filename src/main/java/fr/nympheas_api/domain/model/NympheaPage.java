package fr.nympheas_api.domain.model;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class NympheaPage {

    int page;
    int size;
    long totalElements;
    int totalPages;
    List<Nymphea> content;
}