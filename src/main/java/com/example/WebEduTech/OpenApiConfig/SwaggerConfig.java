package com.example.WebEduTech.OpenApiConfig;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI bibliotecaOpenAPI() {
        return new OpenAPI()
            .info(new Info()
            .title("API - WebEduTech")
            .description("Documentación de las API REST para la gestión de los microservicios de catalogo de cursos, usuarios y carrito de compras")
            .version("v2.0"));
        };
    }
}
