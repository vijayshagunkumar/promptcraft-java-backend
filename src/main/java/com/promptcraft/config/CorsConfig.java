package com.promptcraft.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins(
                        "https://vijayshagunkumar.github.io",
                        "https://vijay-shagunkumar.github.io",
                        "https://prompt-craft-studio.pages.dev",
                        "https://prompt-crafter-pro.pages.dev",
                        "http://localhost:3000",
                        "http://127.0.0.1:3000",
                        "http://localhost:5173"
                )
                .allowedMethods("GET", "POST", "OPTIONS")
                .allowedHeaders("*")
                .exposedHeaders("X-Total-Count", "X-Score-Dimensions")
                .allowCredentials(false)
                .maxAge(3600);
    }
}