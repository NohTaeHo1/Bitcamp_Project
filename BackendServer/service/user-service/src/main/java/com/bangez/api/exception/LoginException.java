package com.bangez.api.exception;

import com.bangez.api.domain.vo.ExceptionStatus;
import lombok.Getter;

@Getter
public class LoginException extends RuntimeException {
    private final ExceptionStatus status;

    public LoginException(ExceptionStatus status, String message) {
        super(message);
        this.status = status;
    }

}
