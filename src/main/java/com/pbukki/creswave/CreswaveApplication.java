package com.pbukki.creswave;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@OpenAPIDefinition(
        info = @Info(
                title="Blog App API Documentation",
                description = "Blog App API Documentation",
                version = "0.0.1",
                contact = @Contact(
                        name = "Philip Bukki",
                        email = "phil.bukki@gmail.com",
                        url = "philb.com"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Learn more about springdoc-openapi",
                url = "https://springdoc.org/"
        )
)
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@SpringBootApplication
@EnableCaching
public class CreswaveApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreswaveApplication.class, args);

    }

}
