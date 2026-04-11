package fr.nympheas_api.infrastructure.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/api/v1/images")
@Tag(name = "Images", description = "Access to Nympheas images")
public class ImageController {

    private static final String IMAGES_CLASSPATH = "data/nymphea_images/";

    @GetMapping("/{filename}")
    @Operation(
            summary = "Get an image",
            description = "Returns the image corresponding to the given filename"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Image returned successfully"),
            @ApiResponse(responseCode = "404", description = "Image not found")
    })
    public ResponseEntity<Resource> getImage(
            @Parameter(description = "Image filename", example = "1914_133_Nymphéas.jpg")
            @PathVariable String filename
    ) {
        String decoded = URLDecoder.decode(filename, StandardCharsets.UTF_8);

        if (decoded.contains("..") || decoded.contains("/") || decoded.contains("\\")) {
            throw new SecurityException("Access denied");
        }

        Resource resource = new ClassPathResource(IMAGES_CLASSPATH + decoded);

        if (!resource.exists() || !resource.isReadable()) {
            throw new NoSuchElementException("Image not found: " + decoded);
        }

        MediaType mediaType = MediaTypeFactory.getMediaType(resource)
                .orElse(MediaType.APPLICATION_OCTET_STREAM);

        return ResponseEntity.ok()
                .contentType(mediaType)
                .body(resource);
    }
}