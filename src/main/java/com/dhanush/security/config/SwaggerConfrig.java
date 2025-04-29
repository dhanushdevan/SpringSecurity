package com.dhanush.security.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfrig {

    @Bean
    public OpenAPI apiCredentials() {
        Server server = new Server();
        server.setUrl("http://localhost:8083"); // Your server URL
        server.setDescription("Local Server");

        Contact contact = new Contact()
                .name("Dhanush")
                .email("pdhanush0326@gmial.com") // Replace with your real email
                .url("..."); // Optional personal URL

        Info info = new Info()
                .title("Dhanush's API Documentation")
                .version("1.0")
                .description("This API provides endpoints for Dhanush's application.")
                .contact(contact);

        return new OpenAPI()
                .info(info)
                .servers(List.of(server));
    }
}
