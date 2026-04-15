package fr.nympheas_api.infrastructure.persistence.mapper;

import fr.nympheas_api.domain.model.Nymphea;
import fr.nympheas_api.infrastructure.persistence.entity.NympheaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NympheaEntityMapper {

    Nymphea toDomain(NympheaEntity entity);

    NympheaEntity toEntity(Nymphea nymphea);
}