package com.promptcraft.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String requestPath = request.getRequestURI();

        // ✅ 0️⃣ SKIP JWT CHECK FOR PUBLIC ENDPOINTS
        if (requestPath.startsWith("/api/v1/auth")
                || requestPath.startsWith("/api/v1/health")) {

            filterChain.doFilter(request, response);
            return;
        }

        // 1️⃣ Read Authorization header
        String authHeader = request.getHeader("Authorization");

        // 2️⃣ Check if header starts with Bearer
        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            // 3️⃣ Extract token
            String token = authHeader.substring(7);

            // 4️⃣ Validate token
            if (jwtUtil.isTokenValid(token)) {

                // 5️⃣ Extract username from token
                String username = jwtUtil.extractUsername(token);

                // 6️⃣ Create authentication object
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                username,
                                null,
                                Collections.emptyList()
                        );

                authentication.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );

                // 7️⃣ Set authentication into security context
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authentication);
            }
        }

        // 8️⃣ Continue filter chain
        filterChain.doFilter(request, response);
    }
}
