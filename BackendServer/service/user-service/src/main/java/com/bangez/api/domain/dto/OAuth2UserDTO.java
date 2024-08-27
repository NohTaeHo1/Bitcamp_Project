package com.bangez.api.domain.dto;

import lombok.Builder;

@Builder
public record OAuth2UserDTO(
        String id,
        String name,
        String email,
        String profile
) {}