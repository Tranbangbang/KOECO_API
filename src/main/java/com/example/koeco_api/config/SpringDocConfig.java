package com.example.koeco_api.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;


@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "API",
                        email = "",
                        url = ""
                ),
                termsOfService = "Terms of service",
                title = "API Document",
                description = "API Document",
                version = "v0.1"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8085"
                ),
                @Server(
                        description = "PROD ENV",
                        url = "https://localhost:8085"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        },
        tags = {
                @Tag(name = "01.Auth", description = "Auth"),
                @Tag(name = "02.User", description = "User"),
                @Tag(name = "03.ADMIN", description = "ADMIN"),
        }
)
//@Configuration
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class SpringDocConfig {
}