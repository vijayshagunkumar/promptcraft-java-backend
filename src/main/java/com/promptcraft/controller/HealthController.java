package com.promptcraft.controller;

import com.promptcraft.api.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HealthController {

    @GetMapping("/health")
    public ApiResponse<String> health() {
        return ApiResponse.success(
                "Service is healthy",
                "PromptCraft Java Backend is healthy 🚀"
        );
    }
}
