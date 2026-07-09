package com.uca.pncparcialfinalhotel.service;

import com.uca.pncparcialfinalhotel.dto.auth.AuthResponse;
import com.uca.pncparcialfinalhotel.dto.auth.LoginRequest;
import com.uca.pncparcialfinalhotel.dto.auth.RefreshTokenRequest;
import com.uca.pncparcialfinalhotel.security.CustomUserDetailsService;
import com.uca.pncparcialfinalhotel.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtService jwtService;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(request.getUsername());

        return AuthResponse.builder()
                .accessToken(jwtService.generateAccessToken(userDetails))
                .refreshToken(jwtService.generateRefreshToken(userDetails))
                .tokenType("Bearer")
                .build();
    }

    public AuthResponse refreshToken(RefreshTokenRequest request) {
        String username = jwtService.extractUsername(request.getRefreshToken());

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        if (!jwtService.isTokenValid(request.getRefreshToken(), userDetails)) {
            throw new RuntimeException("Refresh token inválido");
        }

        return AuthResponse.builder()
                .accessToken(jwtService.generateAccessToken(userDetails))
                .refreshToken(request.getRefreshToken())
                .tokenType("Bearer")
                .build();
    }
}