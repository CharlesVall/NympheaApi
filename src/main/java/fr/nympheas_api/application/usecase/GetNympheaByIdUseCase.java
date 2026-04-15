package fr.nympheas_api.application.usecase;

import fr.nympheas_api.application.dto.NympheaDto;
import fr.nympheas_api.application.mapper.NympheaDtoMapper;
import fr.nympheas_api.domain.port.NympheaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class GetNympheaByIdUseCase {

    private final NympheaRepository nympheaRepository;
    private final NympheaDtoMapper nympheaDtoMapper;
    private final String baseUrl;

    public GetNympheaByIdUseCase(
            NympheaRepository nympheaRepository,
            NympheaDtoMapper nympheaDtoMapper,
            @Value("${nympheas.base-url}") String baseUrl
    ) {
        this.nympheaRepository = nympheaRepository;
        this.nympheaDtoMapper = nympheaDtoMapper;
        this.baseUrl = baseUrl;
    }

    public NympheaDto execute(Long id) {
        return nympheaRepository.findById(id)
                .map(nymphea -> {
                    NympheaDto dto = nympheaDtoMapper.toDto(nymphea);
                    String imageUrl = nymphea.getLocalFile() != null
                            ? baseUrl + "/api/v1/images/" + nymphea.getLocalFile()
                            : null;
                    return NympheaDto.builder()
                            .id(dto.getId())
                            .title(dto.getTitle())
                            .date(dto.getDate())
                            .dimensions(dto.getDimensions())
                            .location(dto.getLocation())
                            .country(dto.getCountry())
                            .statut(dto.getStatut())
                            .localFile(dto.getLocalFile())
                            .imageUrl(imageUrl)
                            .build();
                })
                .orElseThrow(() -> new NoSuchElementException(
                        "Nymphea introuvable pour l'id : " + id
                ));
    }
}