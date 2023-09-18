package com.shared.algo.handler;

import com.shared.algo.exception.BadRequestException;
import com.shared.algo.utils.GenericResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.shared.algo.utils.SharedServiceResponseBuilder.wrapWithErrorResponse;

@RestControllerAdvice
public final class SharedServiceExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public GenericResponse<?> handleBadRequestException(BadRequestException badRequestException) {
        return wrapWithErrorResponse(badRequestException);
    }
}
