package com.uca.pncparcialfinalhotel.controller;

import com.uca.pncparcialfinalhotel.dto.auth.AuthResponse;
import com.uca.pncparcialfinalhotel.dto.auth.LoginRequest;
import com.uca.pncparcialfinalhotel.dto.auth.RefreshTokenRequest;
import com.uca.pncparcialfinalhotel.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(authService.refreshToken(request));
    }
}