package fr.nympheas_api.infrastructure.rest;

import fr.nympheas_api.application.dto.NympheaDto;
import fr.nympheas_api.application.dto.NympheaPageDto;
import fr.nympheas_api.application.usecase.GetAllNympheasUseCase;
import fr.nympheas_api.application.usecase.GetNympheaByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/nympheas")
@RequiredArgsConstructor
public class NympheaController {

    private final GetAllNympheasUseCase getAllNympheasUseCase;
    private final GetNympheaByIdUseCase getNympheaByIdUseCase;

    @GetMapping
    public ResponseEntity<NympheaPageDto> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(getAllNympheasUseCase.execute(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NympheaDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(getNympheaByIdUseCase.execute(id));
    }
}