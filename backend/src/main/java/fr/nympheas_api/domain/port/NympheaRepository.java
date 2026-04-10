package fr.nympheas_api.domain.port;

import fr.nympheas_api.domain.model.Nymphea;
import fr.nympheas_api.domain.model.NympheaPage;

import java.util.List;
import java.util.Optional;

public interface NympheaRepository {

    NympheaPage findAll(int page, int size);

    Optional<Nymphea> findById(Long id);

    Nymphea save(Nymphea nymphea);

    List<Nymphea> saveAll(List<Nymphea> nympheas);
}