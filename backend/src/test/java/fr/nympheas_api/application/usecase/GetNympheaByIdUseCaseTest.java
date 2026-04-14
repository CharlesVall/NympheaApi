package fr.nympheas_api.application.usecase;

import fr.nympheas_api.application.dto.NympheaDto;
import fr.nympheas_api.application.mapper.NympheaDtoMapper;
import fr.nympheas_api.domain.model.Nymphea;
import fr.nympheas_api.domain.port.NympheaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetNympheaByIdUseCaseTest {

    @Mock NympheaRepository nympheaRepository;
    @Mock NympheaDtoMapper nympheaDtoMapper;

    GetNympheaByIdUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new GetNympheaByIdUseCase(nympheaRepository, nympheaDtoMapper, "http://localhost:8080");
    }

    @Test
    void should_return_dto_when_nymphea_exists() {
        Nymphea nymphea = Nymphea.builder()
                .id(1L).title("Nymphéas").date("1906")
                .dimensions("200x200").location("Musée d'Orsay")
                .country("France").statut("existant")
                .localFile("test.jpg")
                .build();

        NympheaDto dto = NympheaDto.builder()
                .id(1L).title("Nymphéas").date("1906")
                .dimensions("200x200").location("Musée d'Orsay")
                .country("France").statut("existant")
                .localFile("test.jpg").imageUrl(null)
                .build();

        when(nympheaRepository.findById(1L)).thenReturn(Optional.of(nymphea));
        when(nympheaDtoMapper.toDto(nymphea)).thenReturn(dto);

        NympheaDto result = useCase.execute(1L);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getTitle()).isEqualTo("Nymphéas");
        assertThat(result.getImageUrl()).isEqualTo("http://localhost:8080/api/v1/images/test.jpg");
        verify(nympheaRepository).findById(1L);
    }

    @Test
    void should_throw_when_nymphea_not_found() {
        when(nympheaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> useCase.execute(99L))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("99");
    }

    @Test
    void should_return_null_image_url_when_local_file_is_null() {
        Nymphea nymphea = Nymphea.builder()
                .id(2L).title("Fragment").date("1910")
                .dimensions("50x50").location("Collection privée")
                .country("").statut("existant")
                .localFile(null)
                .build();

        NympheaDto dto = NympheaDto.builder()
                .id(2L).title("Fragment").date("1910")
                .dimensions("50x50").location("Collection privée")
                .country("").statut("existant")
                .localFile(null).imageUrl(null)
                .build();

        when(nympheaRepository.findById(2L)).thenReturn(Optional.of(nymphea));
        when(nympheaDtoMapper.toDto(nymphea)).thenReturn(dto);

        NympheaDto result = useCase.execute(2L);

        assertThat(result.getImageUrl()).isNull();
    }
}