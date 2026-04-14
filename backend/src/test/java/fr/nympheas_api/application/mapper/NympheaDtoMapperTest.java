package fr.nympheas_api.application.mapper;

import fr.nympheas_api.application.dto.NympheaDto;
import fr.nympheas_api.domain.model.Nymphea;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.*;

class NympheaDtoMapperTest {

    private final NympheaDtoMapper mapper = Mappers.getMapper(NympheaDtoMapper.class);

    @Test
    void should_map_nymphea_to_dto() {
        Nymphea nymphea = Nymphea.builder()
                .id(1L).title("Nymphéas").date("1906")
                .dimensions("200x200").location("Musée d'Orsay")
                .country("France").statut("existant")
                .localFile("test.jpg")
                .build();

        NympheaDto dto = mapper.toDto(nymphea);

        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getTitle()).isEqualTo("Nymphéas");
        assertThat(dto.getDate()).isEqualTo("1906");
        assertThat(dto.getLocalFile()).isEqualTo("test.jpg");
    }

    @Test
    void should_handle_null_fields_gracefully() {
        Nymphea nymphea = Nymphea.builder()
                .id(1L).title(null).date(null)
                .dimensions(null).location(null)
                .country(null).statut(null)
                .localFile(null)
                .build();

        NympheaDto dto = mapper.toDto(nymphea);

        assertThat(dto.getTitle()).isNull();
        assertThat(dto.getLocalFile()).isNull();
    }
}