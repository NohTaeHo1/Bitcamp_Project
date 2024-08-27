package com.bangez.api.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessengerVo {
    private String message;
    private int status;
    private String accessToken;
    private boolean exist;
    private String refreshToken;
    private Long id;
}
