package fr.nympheas_api.application.mapper;

import fr.nympheas_api.application.dto.NympheaDto;
import fr.nympheas_api.application.dto.NympheaPageDto;
import fr.nympheas_api.domain.model.Nymphea;
import fr.nympheas_api.domain.model.NympheaPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NympheaDtoMapper {

    @Mapping(target = "imageUrl", ignore = true)
    NympheaDto toDto(Nymphea nymphea);

    NympheaPageDto toPageDto(NympheaPage page);
}