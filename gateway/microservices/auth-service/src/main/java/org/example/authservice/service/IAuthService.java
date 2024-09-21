package org.example.authservice.service;

import api.ResponseApi;
import org.example.authservice.dto.AuthRequest;
import org.example.authservice.dto.AuthResponse;

public interface IAuthService {
    ResponseApi<AuthResponse> register(AuthRequest authRequest);

    ResponseApi<AuthResponse> refreshToken(String refreshToken);

    ResponseApi<AuthResponse> login(AuthRequest authRequest);
}
