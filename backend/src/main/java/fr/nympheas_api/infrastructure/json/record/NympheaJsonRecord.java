package fr.nympheas_api.infrastructure.json.record;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NympheaJsonRecord {

    private String title;
    private String date;
    private String dimensions;
    private String location;
    private String country;
    private String statut;

    @JsonProperty("local_file")
    private String localFile;
}