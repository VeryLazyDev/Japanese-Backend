package com.dev.japanese_app.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .servers(List.of(
                        new Server()
                                .url("https://api-muda-zero.nyinyimyintmyat.com")
                ))
                .info(new Info()
                        .title("MUDA ZERO API DOCUMENTATION")
                        .description("Details API documentation for all endpoints")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("orion")
                                .email("nnmm5122004@gmail.com")
                        )
                )
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .components(new Components().
                        addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()));

    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }

    @Bean
    public GroupedOpenApi groupedOpenApi() {
        return GroupedOpenApi.builder()
                .group("v1")
                .packagesToScan("com.dev.japanese_app")
                .build();
    }


}
