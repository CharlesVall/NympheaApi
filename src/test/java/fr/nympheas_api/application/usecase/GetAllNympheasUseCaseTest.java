package fr.nympheas_api.application.usecase;

import fr.nympheas_api.application.dto.NympheaDto;
import fr.nympheas_api.application.dto.NympheaPageDto;
import fr.nympheas_api.application.mapper.NympheaDtoMapper;
import fr.nympheas_api.domain.model.Nymphea;
import fr.nympheas_api.domain.model.NympheaPage;
import fr.nympheas_api.domain.port.NympheaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetAllNympheasUseCaseTest {

    @Mock NympheaRepository nympheaRepository;
    @Mock NympheaDtoMapper nympheaDtoMapper;

    GetAllNympheasUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new GetAllNympheasUseCase(nympheaRepository, nympheaDtoMapper, "http://localhost:8080");
    }

    @Test
    void should_return_paged_dtos() {
        Nymphea nymphea = Nymphea.builder()
                .id(1L).title("Nymphéas").date("1906")
                .dimensions("200x200").location("Musée d'Orsay")
                .country("France").statut("existant")
                .localFile("test.jpg")
                .build();

        NympheaPage page = NympheaPage.builder()
                .content(List.of(nymphea))
                .page(0).size(20)
                .totalElements(1L).totalPages(1)
                .build();

        NympheaDto dto = NympheaDto.builder()
                .id(1L).title("Nymphéas").date("1906")
                .dimensions("200x200").location("Musée d'Orsay")
                .country("France").statut("existant")
                .localFile("test.jpg").imageUrl(null)
                .build();

        when(nympheaRepository.findAll(0, 20)).thenReturn(page);
        when(nympheaDtoMapper.toDto(nymphea)).thenReturn(dto);

        NympheaPageDto result = useCase.execute(0, 20);

        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getTotalElements()).isEqualTo(1L);
        assertThat(result.getContent().getFirst().getImageUrl())
                .isEqualTo("http://localhost:8080/api/v1/images/test.jpg");
    }

    @Test
    void should_return_empty_page_when_no_results() {
        NympheaPage emptyPage = NympheaPage.builder()
                .content(List.of())
                .page(0).size(20)
                .totalElements(0L).totalPages(0)
                .build();

        when(nympheaRepository.findAll(0, 20)).thenReturn(emptyPage);

        NympheaPageDto result = useCase.execute(0, 20);

        assertThat(result.getContent()).isEmpty();
        assertThat(result.getTotalElements()).isZero();
    }
}