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
                        .description("Catalogue exhaustif des Nymphéas de Claude Monet")
                        .version("v1")
                        .contact(new Contact()
                                .name("Nymphéas API")
                                .url("https://github.com/CharlesVall/NympheaApi")));
    }
}