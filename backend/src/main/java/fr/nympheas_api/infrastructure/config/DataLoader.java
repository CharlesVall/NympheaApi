package fr.nympheas_api.infrastructure.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.nympheas_api.domain.model.Nymphea;
import fr.nympheas_api.domain.port.NympheaRepository;
import fr.nympheas_api.infrastructure.json.mapper.NympheaJsonMapper;
import fr.nympheas_api.infrastructure.json.record.NympheaJsonRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {

    private final NympheaRepository nympheaRepository;
    private final ObjectMapper objectMapper;
    private final NympheaJsonMapper nympheaJsonMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (nympheaRepository.findAll(0, 1).getTotalElements() > 0) {
            log.info("DataLoader — db already filed");
            return;
        }

        InputStream inputStream = new ClassPathResource("data/catalogue.json")
                .getInputStream();

        List<NympheaJsonRecord> records = objectMapper.readValue(
                inputStream,
                new TypeReference<>() {}
        );

        List<Nymphea> nympheas = records.stream()
                .map(nympheaJsonMapper::toDomain)
                .toList();

        nympheaRepository.saveAll(nympheas);
        log.info("DataLoader — {} nympheas loaded", nympheas.size());
    }
}