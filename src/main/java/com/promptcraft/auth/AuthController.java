package com.promptcraft.auth;

import com.promptcraft.dto.auth.LoginRequest;
import com.promptcraft.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        // ✅ TEMP HARD-CODED USER (FOR DEVELOPMENT)
        if (!"admin".equals(request.getUsername())
                || !"admin123".equals(request.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        String token = jwtUtil.generateToken(request.getUsername());

        return ResponseEntity.ok(
                Map.of(
                        "success", true,
                        "message", "Login successful",
                        "data", Map.of(
                                "token", token,
                                "tokenType", "Bearer"
                        ),
                        "timestamp", LocalDateTime.now().toString()
                )
        );
    }
}
