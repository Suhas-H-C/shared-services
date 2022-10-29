package com.shared.algo.controller;

import static com.shared.algo.enums.ContentStatus.DISPOSITION;
import static com.shared.algo.utils.SharedAlgosResponseBuilder.wrapWithGenericResponse;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shared.algo.exception.BadRequestException;
import com.shared.algo.model.IpData;
import com.shared.algo.service.CsvService;
import com.shared.algo.service.JsonContentService;
import com.shared.algo.service.PDFService;
import com.shared.algo.service.StringContentUtilsService;
import com.shared.algo.service.XlsxService;
import com.shared.algo.utils.GenericResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/file")
public class FileController {

	@Autowired
	private StringContentUtilsService stringContentUtils;
	@Autowired
	private JsonContentService jsonContentService;
	@Autowired
	private CsvService csvService;
	@Autowired
	private XlsxService xlsxService;
	@Autowired
	private PDFService pdfService;

	@Operation(method = "GET", description = "Retrieves the string from file", tags = "file")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Success"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "403", description = "Forbidden"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@GetMapping(value = "/fetch-content")
	public ResponseEntity<GenericResponse<?>> getContent(
			@RequestParam(name = "fileName", required = true) String fileName) {
		try {
			return new ResponseEntity<>(wrapWithGenericResponse(stringContentUtils.getContent(fileName)),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(wrapWithGenericResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@Operation(method = "GET", description = "Retrieves the json from file", tags = "file")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Success"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "403", description = "Forbidden"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@GetMapping(value = "/fetch-json")
	public ResponseEntity<GenericResponse<?>> getJson(
			@RequestParam(name = "stringPath", required = true) String stringPath) {
		try {
			return new ResponseEntity<>(
					wrapWithGenericResponse(jsonContentService.fetchJsonData(IpData.class, stringPath)), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(wrapWithGenericResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@Operation(method = "POST", description = "Retrieves the data from CSV file", tags = "csv-file")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Success"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "403", description = "Forbidden"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@PostMapping(value = "/read-csv", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponse<?>> readCSV(
			@RequestParam(name = "file", required = true) MultipartFile multipartFile) {
		try {
			return new ResponseEntity<>(wrapWithGenericResponse(csvService.retrieveData(multipartFile, IpData.class)),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(wrapWithGenericResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@Operation(method = "GET", description = "Produces CSV file", tags = "csv-file")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Success"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "403", description = "Forbidden"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@GetMapping(value = "/produce-csv", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> getCSV(
			@RequestParam(value = "fileName", required = true, defaultValue = "data") String fileName) {
		try {
			return ResponseEntity.ok()
					.header(DISPOSITION.getKey(), DISPOSITION.getContent().concat(fileName).concat(".csv"))
					.body(new InputStreamResource(
							csvService.getCSV((List<?>) jsonContentService.fetchJsonData(IpData.class, "ipData"))));
		} catch (Exception e) {
			return new ResponseEntity<>(wrapWithGenericResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@Operation(method = "GET", description = "Reads Excel file", tags = "xlsx-file")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Success"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "403", description = "Forbidden"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@GetMapping(value = "/read-xlsx", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> readExcel(@RequestParam(value = "file", required = true) MultipartFile multipartFile) {
		try {
			return new ResponseEntity<>(wrapWithGenericResponse(xlsxService.read(multipartFile, IpData.class)),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(wrapWithGenericResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@Operation(method = "GET", description = "Produces Excel file", tags = "xlsx-file")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Success"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "403", description = "Forbidden"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@GetMapping(value = "/produce-xlsx", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> getExcel(@RequestParam(value = "fileName", required = true) String fileName) {
		try {
			return ResponseEntity.ok()
					.header(DISPOSITION.getKey(), DISPOSITION.getContent().concat(fileName).concat(".xlsx"))
					.body(new InputStreamResource(
							xlsxService.write((List<?>) jsonContentService.fetchJsonData(IpData.class, "ipData"))));
		} catch (Exception e) {
			return new ResponseEntity<>(wrapWithGenericResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@Operation(method = "GET", description = "Produces PDF file", tags = "pdf-file")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Success"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "403", description = "Forbidden"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@GetMapping(value = "/produce-pdf", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void getPDF(@RequestParam(value = "fileName", required = true, defaultValue = "sample") String fileName,
			HttpServletResponse response) {
		try {
			response.setContentType("application/pdf");
			response.setHeader(DISPOSITION.getKey(), DISPOSITION.getContent().concat(fileName).concat(".pdf"));
			pdfService.write(response);
		} catch (Exception e) {
			throw new BadRequestException(e.getMessage());
		}
	}
}
