package fr.nympheas_api.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI nympheasOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Nymphéas API")
                        .description("Exhaustive catalog of Claude Monet Nympheas")
                        .version("v1")
                        .contact(new Contact()
                                .name("Nympheas API - Git repository")
                                .url("https://github.com/CharlesVall/NympheaApi")));
    }
}