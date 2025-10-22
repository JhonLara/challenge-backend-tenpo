package com.tenpo.challenge.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI dynamicPercentageOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Dynamic Percentage API")
                        .description("API para cálculo con porcentaje dinámico, caché con TTL de 30 minutos y registro asíncrono de historial en PostgreSQL.")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0"))
                        .contact(new Contact().name("Equipo").email("team@example.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("Repositorio y documentación")
                        .url("https://example.com/repo"));
    }
}
