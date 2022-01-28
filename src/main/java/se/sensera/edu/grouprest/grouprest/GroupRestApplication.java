package se.sensera.edu.grouprest.grouprest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveUserDetailsServiceAutoConfiguration;
import org.springframework.web.reactive.config.EnableWebFlux;

@OpenAPIDefinition(servers = {@Server(url = "/", description = "Default Server URL")})
@SpringBootApplication(exclude = ReactiveUserDetailsServiceAutoConfiguration.class)
@EnableWebFlux
public class GroupRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(GroupRestApplication.class, args);
    }

}
