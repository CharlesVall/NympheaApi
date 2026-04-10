package fr.nympheas_api.application.usecase;

import fr.nympheas_api.application.dto.NympheaPageDto;
import fr.nympheas_api.application.mapper.NympheaDtoMapper;
import fr.nympheas_api.domain.port.NympheaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAllNympheasUseCase {

    private final NympheaRepository nympheaRepository;
    private final NympheaDtoMapper nympheaDtoMapper;

    public NympheaPageDto execute(int page, int size) {
        return nympheaDtoMapper.toPageDto(
                nympheaRepository.findAll(page, size)
        );
    }
}