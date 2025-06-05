package com.italika.challenge.infrastructure.adapter.controller;

import com.italika.challenge.application.service.AuthService;
import com.italika.challenge.domain.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {
        String token = authService.generarToken(usuario);
        return ResponseEntity.ok(token);
    }
}