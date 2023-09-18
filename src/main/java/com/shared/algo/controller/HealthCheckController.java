package com.shared.algo.controller;

import com.shared.algo.controller.documentation.HealthCheckControllerDocumentation;
import com.shared.algo.utils.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.shared.algo.utils.SharedServiceResponseBuilder.wrapWithGenericResponse;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/health")
public final class HealthCheckController implements HealthCheckControllerDocumentation {

    @GetMapping(value = "/health-check", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse<?>> healthCheck() {
        return new ResponseEntity<>(wrapWithGenericResponse("Health Check success"), HttpStatus.OK);
    }
}
