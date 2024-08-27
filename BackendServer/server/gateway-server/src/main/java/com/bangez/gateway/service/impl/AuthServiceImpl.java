package com.bangez.gateway.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import com.bangez.gateway.domain.dto.LoginDTO;
import com.bangez.gateway.domain.model.PrincipalUserDetails;
import com.bangez.gateway.domain.vo.ExceptionStatus;
import com.bangez.gateway.exception.GatewayException;
import com.bangez.gateway.provider.JwtTokenProvider;
import com.bangez.gateway.service.AuthService;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final WebClient webClient;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public Mono<ServerResponse> localLogin(LoginDTO dto) {
        return Mono.just(dto)
        .log()
        .flatMap(i -> 
            webClient.post()
            .uri("lb://user-service/auth/login/local")
            .accept(MediaType.APPLICATION_JSON)
            .bodyValue(i)
            .retrieve()
            .bodyToMono(PrincipalUserDetails.class)
        )
        .log()
        .doOnNext(i -> log.info(">>> PrincipalUserDetails: {}", i.toString()))
        .flatMap(i -> 
            jwtTokenProvider.generateToken(i, false)
            .flatMap(accessToken -> 
                jwtTokenProvider.generateToken(i, true)
                .flatMap(refreshToken -> 
                    ServerResponse.ok()
                    .cookie(
                        ResponseCookie.from("accessToken")
                        .value(accessToken)
                        .maxAge(jwtTokenProvider.getAccessTokenExpired())
                        .path("/")
                        // .httpOnly(true)
                        .build()
                    )
                    .cookie(
                        ResponseCookie.from("refreshToken")
                        .value(refreshToken)
                        .maxAge(jwtTokenProvider.getRefreshTokenExpired())
                        .path("/")
                                .domain(".taeho.site")
                                .secure(true)
//                         .httpOnly(true)
                        .build()
                    )
                    .build()
                )
            )
        )
        .log()
        .onErrorMap(Exception.class, e -> new GatewayException(ExceptionStatus.UNAUTHORIZED, "Invalid User"))
        .switchIfEmpty(Mono.error(new GatewayException(ExceptionStatus.UNAUTHORIZED, "Invalid User")))
        .onErrorResume(GatewayException.class, e -> ServerResponse.status(e.getStatus().getStatus().value()).bodyValue(e.getMessage()))
        ;
    }

    @Override
    public Mono<ServerResponse> refresh(String refreshToken) {
        return Mono.just(refreshToken)
        .flatMap(bearerToken -> Mono.just(jwtTokenProvider.removeBearer(bearerToken)))
        .filter(jwtToken -> jwtTokenProvider.isTokenValid(jwtToken, true))
        .switchIfEmpty(Mono.error(new GatewayException(ExceptionStatus.UNAUTHORIZED, "Invalid Refresh Token")))
        .flatMap(jwtToken -> 
            Mono.just(jwtTokenProvider.extractPrincipalUserDetails(jwtToken))
            .filterWhen(user -> jwtTokenProvider.isTokenInRedis(user.getUsername(), jwtToken))
            .switchIfEmpty(Mono.error(new GatewayException(ExceptionStatus.UNAUTHORIZED, "Token not found in Redis")))
            .flatMap(i -> jwtTokenProvider.generateToken(i, false))
        )
        .flatMap(accessToken -> 
            ServerResponse.ok()
            .cookie(
                ResponseCookie.from("accessToken")
                .value(accessToken)
                .maxAge(jwtTokenProvider.getAccessTokenExpired())
                .path("/")
                .domain(".taeho.site")
                // .httpOnly(true)
                .build()
            )
            .build()    
        )
        .onErrorResume(GatewayException.class, e -> ServerResponse.status(e.getStatus().getStatus().value()).bodyValue(e.getMessage()));
    }

    @Override
    public Mono<ServerResponse> logout(String refreshToken) {
        return Mono.just(refreshToken)
        .flatMap(bearerToken -> Mono.just(jwtTokenProvider.removeBearer(bearerToken)))
        .filter(jwtToken -> jwtTokenProvider.isTokenValid(jwtToken, false))
        .switchIfEmpty(Mono.error(new GatewayException(ExceptionStatus.UNAUTHORIZED, "Invalid Refresh Token")))
        .flatMap(jwtToken -> 
            Mono.just(jwtTokenProvider.extractPrincipalUserDetails(jwtToken))
            .filterWhen(user -> jwtTokenProvider.removeTokenInRedis(user.getUsername()))
            .switchIfEmpty(Mono.error(new GatewayException(ExceptionStatus.UNAUTHORIZED, "Token not found in Redis")))
        )
        .flatMap(i -> ServerResponse.ok().build())
        .onErrorResume(GatewayException.class, e -> ServerResponse.status(e.getStatus().getStatus().value()).bodyValue(e.getMessage()));
    }
 
}
