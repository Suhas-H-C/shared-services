package com.shared.algo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public final class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String message;

    @Override
    public String getMessage() {
        return this.message;
    }
}
