package com.example.demo.security;

import com.example.demo.controller.auth.dto.AuthRequest;
import com.example.demo.controller.auth.dto.AuthResponse;
import com.example.demo.controller.auth.dto.RegisterRequest;
import lombok.NonNull;

import java.util.UUID;

public interface AuthService {
    UUID getAuthorizedUserId();

    void register(@NonNull RegisterRequest request);

    AuthResponse authenticate(@NonNull AuthRequest request);
}
