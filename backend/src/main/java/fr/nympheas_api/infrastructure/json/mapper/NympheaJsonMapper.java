package fr.nympheas_api.infrastructure.json.mapper;

import fr.nympheas_api.domain.model.Nymphea;
import fr.nympheas_api.infrastructure.json.record.NympheaJsonRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NympheaJsonMapper {

    @Mapping(target = "id", ignore = true)
    Nymphea toDomain(NympheaJsonRecord record);
}