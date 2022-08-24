package com.challenge.tenpo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.util.List;

@Configuration
public class OpenApiConfiguration {

    @Value("${springdoc.version: 0.0.1}")
    private String appVersion;

    private Info info() {
        return new Info()
                .title("Tenpo Challenge Service")
                .version(appVersion);
    }


    @Bean
    public OpenAPI customOpenApiCustomizer() {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .components(
                        new Components()
                                .addSecuritySchemes(securitySchemeName,
                                        new SecurityScheme()
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                )
                .security(List.of(new SecurityRequirement().addList(securitySchemeName)))
                .info(new Info().title("Tenpo challenge").version(appVersion));
    }
}

