package org.example.authservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.authservice.dto.AuthRequest;
import org.example.authservice.service.IAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthService _authService;

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(_authService.login(authRequest));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(_authService.register(authRequest));
    }

    @PostMapping(value = "/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody String refreshToken) {
        return ResponseEntity.ok(_authService.refreshToken(refreshToken));
    }


}
