package fr.nympheas_api.infrastructure;

import fr.nympheas_api.application.dto.ErrorResponse;
import fr.nympheas_api.application.dto.NympheaDto;
import fr.nympheas_api.application.dto.NympheaPageDto;
import fr.nympheas_api.application.usecase.GetAllNympheasUseCase;
import fr.nympheas_api.application.usecase.GetNympheaByIdUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/nympheas")
@RequiredArgsConstructor
@Tag(name = "Nympheas", description = "Claude Monet Nympheas catalog")
public class NympheaController {

    private final GetAllNympheasUseCase getAllNympheasUseCase;
    private final GetNympheaByIdUseCase getNympheaByIdUseCase;

    @GetMapping
    @Operation(
            summary = "List nympheas",
            description = "Return a paginated list of nymphea from the catalog"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Nymphea page return with success",
                    content = @Content(schema = @Schema(implementation = NympheaPageDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Pagination parameters invalid",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    public ResponseEntity<NympheaPageDto> getAll(
            @Parameter(description = "Pagination start at 0", example = "0")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Element by page", example = "20")
            @RequestParam(defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(getAllNympheasUseCase.execute(page, size));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get a nymphea by id",
            description = "Return detail of a nymphea with his identifier"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Nymphea found",
                    content = @Content(schema = @Schema(implementation = NympheaDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Nymphea not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid id",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    public ResponseEntity<NympheaDto> getById(
            @Parameter(description = "Nymphea unique identifier", example = "1")
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(getNympheaByIdUseCase.execute(id));
    }
}