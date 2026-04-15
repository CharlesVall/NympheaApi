package fr.nympheas_api.domain.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Nymphea {

    Long id;
    String title;
    String date;
    String dimensions;
    String location;
    String country;
    String statut;
    String localFile;

}