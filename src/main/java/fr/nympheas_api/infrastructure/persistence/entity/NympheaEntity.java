package fr.nympheas_api.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "nympheas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NympheaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String date;
    private String dimensions;
    private String location;
    private String country;
    private String statut;
    private String localFile;
}