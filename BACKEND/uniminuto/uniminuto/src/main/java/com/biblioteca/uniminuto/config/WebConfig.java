package com.biblioteca.uniminuto.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Aplica a todas las rutas
                .allowedOrigins("http://localhost", "http://127.0.0.1")  // Permitir solicitudes de ambos orígenes
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Permitir estos métodos HTTP
                .allowedHeaders("*")  // Permitir todos los encabezados
                .allowCredentials(true);  // Permitir envío de cookies
    }
}