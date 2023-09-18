package com.shared.info.controller;

import com.shared.info.utils.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class HealthCheckControllerTest {

    private final HealthCheckController controller = new HealthCheckController();

    @Test
    void should_return_valid_response_when_health_check_is_initialized() {
        ResponseEntity<GenericResponse<?>> actualResponse = controller.healthCheck();
        log.info("{}", Objects.requireNonNull(actualResponse.getBody()).data());
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
    }
}
