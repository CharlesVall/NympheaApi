package fr.nympheas_api.infrastructure.persistence.repository.adapter;

import fr.nympheas_api.domain.model.Nymphea;
import fr.nympheas_api.domain.model.NympheaPage;
import fr.nympheas_api.domain.port.NympheaRepository;
import fr.nympheas_api.infrastructure.persistence.entity.NympheaEntity;
import fr.nympheas_api.infrastructure.persistence.mapper.NympheaEntityMapper;
import fr.nympheas_api.infrastructure.persistence.repository.jpa.NympheaJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class NympheaRepositoryAdapter implements NympheaRepository {

    private final NympheaJpaRepository jpaRepository;
    private final NympheaEntityMapper entityMapper;

    @Override
    public NympheaPage findAll(int page, int size) {
        Page<NympheaEntity> result = jpaRepository.findAll(PageRequest.of(page, size));
        List<Nymphea> content = result.getContent()
                .stream()
                .map(entityMapper::toDomain)
                .toList();

        return NympheaPage.builder()
                .content(content)
                .page(result.getNumber())
                .size(result.getSize())
                .totalElements(result.getTotalElements())
                .totalPages(result.getTotalPages())
                .build();
    }

    @Override
    public Optional<Nymphea> findById(Long id) {
        return jpaRepository.findById(id)
                .map(entityMapper::toDomain);
    }

    @Override
    public Nymphea save(Nymphea nymphea) {
        return entityMapper.toDomain(
                jpaRepository.save(entityMapper.toEntity(nymphea))
        );
    }

    @Override
    public List<Nymphea> saveAll(List<Nymphea> nympheas) {
        return jpaRepository.saveAll(
                        nympheas.stream().map(entityMapper::toEntity).toList()
                )
                .stream()
                .map(entityMapper::toDomain)
                .toList();
    }
}