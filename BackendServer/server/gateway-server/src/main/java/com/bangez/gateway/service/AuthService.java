package com.bangez.gateway.service;

import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import com.bangez.gateway.domain.dto.LoginDTO;

public interface AuthService {
    Mono<ServerResponse> localLogin(LoginDTO dto);
    Mono<ServerResponse> refresh(String refreshToken);
    Mono<ServerResponse> logout(String refreshToken);
}
