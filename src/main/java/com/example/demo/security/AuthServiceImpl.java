package com.example.demo.security;

import com.example.demo.action.user.create.CreateCustomUserAction;
import com.example.demo.action.user.create.CreateCustomUserActionArgument;
import com.example.demo.controller.auth.dto.AuthRequest;
import com.example.demo.controller.auth.dto.AuthResponse;
import com.example.demo.controller.auth.dto.RegisterRequest;
import com.example.demo.model.CustomUser;
import com.example.demo.service.user.CustomUserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final CustomUserService service;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final CreateCustomUserAction createCustomUserAction;

    @Override
    public UUID getAuthorizedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUser details = (CustomUser) authentication.getPrincipal();
        return details.getId();
    }

    @Override
    public void register(@NonNull RegisterRequest request) {
        CustomUser user = createCustomUserAction.execute(CreateCustomUserActionArgument.builder()
                                                                                       .email(request.getEmail())
                                                                                       .password(passwordEncoder.encode(request.getPassword()))
                                                                                       .build());
    }

    @Override
    public AuthResponse authenticate(@NonNull AuthRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = service.findByEmail(request.getEmail());


        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                           .token(jwtToken)
                           .build();
    }
}
