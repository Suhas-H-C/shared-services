package com.shared.algo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shared.algo.model.IpData;
import com.shared.algo.service.CsvService;
import com.shared.algo.service.JsonContentService;
import com.shared.algo.service.StringContentUtils;
import com.shared.algo.utils.GenericResponse;
import static com.shared.algo.utils.SharedAlgosResponseBuilder.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/file")
public class FileController {

	@Autowired
	private StringContentUtils stringContentUtils;

	@Autowired
	private JsonContentService jsonContentService;
	
	@Autowired
	private CsvService csvService;
	
	
	@Operation(method = "GET", description = "Retreives the string from file", tags = "file")
	@ApiResponses({@ApiResponse(responseCode = "200", description = "Success"),
		@ApiResponse(responseCode = "400", description = "Bad Request"),
		@ApiResponse(responseCode = "401", description = "Unauthorized"),
		@ApiResponse(responseCode = "403", description = "Forbidden"),
		@ApiResponse(responseCode = "500", description = "Internal Server Error")})
	@GetMapping(value = "/fetch-content")
	public ResponseEntity<GenericResponse<?>> getContent(@RequestParam(name = "fileName", required = true) String fileName){
		try {
			return new ResponseEntity<>
			(wrapWithGenericResponse(stringContentUtils.getContent(fileName)), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(wrapWithGenericResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@Operation(method = "GET", description = "Retreives the json from file", tags = "file")
	@ApiResponses({@ApiResponse(responseCode = "200", description = "Success"),
		@ApiResponse(responseCode = "400", description = "Bad Request"),
		@ApiResponse(responseCode = "401", description = "Unauthorized"),
		@ApiResponse(responseCode = "403", description = "Forbidden"),
		@ApiResponse(responseCode = "500", description = "Internal Server Error")})
	@GetMapping(value = "/fetch-json")
	public ResponseEntity<GenericResponse<?>> getJson(@RequestParam(name = "stringPath", required = true) String stringPath){
		try {
			return new ResponseEntity<>
			(wrapWithGenericResponse(jsonContentService.fetchJsonData(IpData.class, stringPath)), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(wrapWithGenericResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@Operation(method = "POST", description = "Retreives the data from CSV file", tags = "file")
	@ApiResponses({@ApiResponse(responseCode = "200", description = "Success"),
		@ApiResponse(responseCode = "400", description = "Bad Request"),
		@ApiResponse(responseCode = "401", description = "Unauthorized"),
		@ApiResponse(responseCode = "403", description = "Forbidden"),
		@ApiResponse(responseCode = "500", description = "Internal Server Error")})
	@PostMapping(value = "/fetch-csv", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponse<?>> getCSV(@RequestParam(name = "file", required = true) MultipartFile multipartFile){
		try {
			return new ResponseEntity<>
			(wrapWithGenericResponse(csvService.retrieveData(multipartFile)), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(wrapWithGenericResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
}
