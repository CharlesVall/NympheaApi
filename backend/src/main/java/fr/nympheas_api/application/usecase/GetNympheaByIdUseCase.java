package fr.nympheas_api.application.usecase;

import fr.nympheas_api.application.dto.NympheaDto;
import fr.nympheas_api.application.mapper.NympheaDtoMapper;
import fr.nympheas_api.domain.model.Nymphea;
import fr.nympheas_api.domain.port.NympheaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class GetNympheaByIdUseCase {

    private final NympheaRepository nympheaRepository;
    private final NympheaDtoMapper nympheaDtoMapper;

    public NympheaDto execute(Long id) {
        return nympheaRepository.findById(id)
                .map(nympheaDtoMapper::toDto)
                .orElseThrow(() -> new NoSuchElementException(
                        "Nymphea not found at id : " + id
                ));
    }
}