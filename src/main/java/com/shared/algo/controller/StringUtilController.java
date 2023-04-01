package com.shared.algo.controller;

import com.shared.algo.model.InternetProtocol;
import com.shared.algo.service.StringContentUtilsService;
import com.shared.algo.utils.GenericResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.shared.algo.utils.SharedAlgosResponseBuilder.wrapWithGenericResponse;

@RestController
@RequestMapping(value = "/str")
public final class StringUtilController {

    @Autowired
    private StringContentUtilsService stringContentUtils;

    @Operation(method = "GET", description = "Retrieves the string from file", tags = "file")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @GetMapping(value = "/fetch-fields")
    public ResponseEntity<GenericResponse<?>> getFields() {
        try {
            return new ResponseEntity<>(wrapWithGenericResponse(stringContentUtils.getFields(InternetProtocol.class)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(wrapWithGenericResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
