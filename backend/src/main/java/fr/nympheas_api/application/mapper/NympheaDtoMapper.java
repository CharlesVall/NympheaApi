package fr.nympheas_api.application.mapper;

import fr.nympheas_api.application.dto.NympheaDto;
import fr.nympheas_api.application.dto.NympheaPageDto;
import fr.nympheas_api.domain.model.Nymphea;
import fr.nympheas_api.domain.model.NympheaPage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NympheaDtoMapper {

    NympheaDto toDto(Nymphea nymphea);

    NympheaPageDto toPageDto(NympheaPage page);
}