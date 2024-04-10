package com.example.liquidbits_springboot.swaggerConfig;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "LiquidBits",
                description = "smarte Schankanlage",
                contact = @Contact (name = "Hubert Lercher", email = "hubert.lercher@edu.htl-klu.at")
        ),
        externalDocs = @ExternalDocumentation(description = "Endpoint Definition liquidBits")
        )
        public class swaggerConfiguration {
}
