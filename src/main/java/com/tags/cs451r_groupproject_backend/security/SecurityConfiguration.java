package com.tags.cs451r_groupproject_backend.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.time.Duration;
import java.util.List;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration {
    private JWTAuthenticationFilter jwtAuthenticationFilter;
    private AuthenticationProvider authenticationProvider;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors(customizer -> {
                    customizer.configurationSource(request -> {
                        CorsConfiguration configuration = new CorsConfiguration();
                        configuration.setAllowedOrigins(List.of("http://127.0.0.1:5500"));
                        configuration.setAllowedMethods(List.of("*"));
                        configuration.setAllowCredentials(true);
                        configuration.setAllowedHeaders(List.of("*"));
                        configuration.setMaxAge(Duration.ofSeconds(3600));
                        return configuration;
                    });
        }
        );
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        //TODO: set the request matchers
        httpSecurity.authorizeHttpRequests(request ->
                request.requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/files/{id}").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/admin/**").authenticated()
                        .anyRequest().authenticated()
        );
        httpSecurity.sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
        httpSecurity.authenticationProvider(authenticationProvider);
        httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

}
