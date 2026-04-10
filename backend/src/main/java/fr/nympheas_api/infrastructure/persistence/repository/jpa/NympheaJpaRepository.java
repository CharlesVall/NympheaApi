package fr.nympheas_api.infrastructure.persistence.repository.jpa;

import fr.nympheas_api.infrastructure.persistence.entity.NympheaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NympheaJpaRepository extends
        JpaRepository<NympheaEntity, Long> {
}