package se.sensera.edu.grouprest.grouprest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoders;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers.pathMatchers;

@Configuration
//@EnableWebSecurity
@EnableWebFluxSecurity
class SecurityConfig {
    private final Environment env;

    public SecurityConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(final ServerHttpSecurity http) {
        return http
                .authorizeExchange()
                //.anyExchange().authenticated()
                .matchers(pathMatchers("/api/**")).authenticated() // require a logged-in user for api calls
                .anyExchange().permitAll()
                .and().httpBasic().disable()
                .csrf().disable()
                .oauth2Client()
                //.and()
                //.oauth2Login()
                .and()
                .oauth2ResourceServer()
                    .jwt()
                    .and()
                .and()
                .build();
    }

    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        return ReactiveJwtDecoders.fromOidcIssuerLocation(env.getRequiredProperty("spring.security.oauth2.client.provider.keycloak.issuer-uri"));
    }
}
