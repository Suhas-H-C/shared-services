package com.shared.algo.handler;

import static com.shared.algo.utils.SharedAlgosResponseBuilder.wrapWithErrorResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.shared.algo.exception.BadRequestException;
import com.shared.algo.utils.GenericResponse;

@RestControllerAdvice
public final class SharedAlgoExceptionHandler {

	@ExceptionHandler(BadRequestException.class)
	public GenericResponse<?> handleBadRequestException(BadRequestException badRequestException) {
		return wrapWithErrorResponse(badRequestException);
	}
}
