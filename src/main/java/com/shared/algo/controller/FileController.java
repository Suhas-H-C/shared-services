package com.shared.algo.controller;

import com.shared.algo.controller.documentation.FileControllerDocumentation;
import com.shared.algo.exception.BadRequestException;
import com.shared.algo.model.InternetProtocol;
import com.shared.algo.service.*;
import com.shared.algo.utils.GenericResponse;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.util.List;

import static com.shared.algo.enums.ContentStatus.DISPOSITION;
import static com.shared.algo.utils.SharedServiceResponseBuilder.wrapWithGenericResponse;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/file")
@AllArgsConstructor
public final class FileController implements FileControllerDocumentation {

    private final TextContentParserService stringContentUtils;
    private final JsonContentService jsonContentService;
    private final CsvService csvService;
    private final XlsxService xlsxService;
    private final PDFService pdfService;
    private final ReportService reportService;


    @GetMapping(value = "/fetch-content")
    public ResponseEntity<GenericResponse<?>> getFileContentAsString(@RequestParam(name = "fileName") String fileName) {
        try {
            return new ResponseEntity<>(wrapWithGenericResponse(stringContentUtils.parseTextContent(fileName)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(wrapWithGenericResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/fetch-json")
    public ResponseEntity<GenericResponse<?>> getJsonFromFile(@RequestParam(name = "stringPath") String stringPath) {
        try {
            return new ResponseEntity<>(wrapWithGenericResponse(jsonContentService.fetchJsonData(InternetProtocol.class, stringPath)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(wrapWithGenericResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping(value = "/read-csv", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse<?>> readCSV(@RequestParam(name = "file") MultipartFile multipartFile, @RequestParam(name = "type") String contentType) {
        try {
            return new ResponseEntity<>(wrapWithGenericResponse(csvService.readCSVFileContent(multipartFile, contentType)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(wrapWithGenericResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/produce-csv", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> generateCSV(@RequestParam(value = "fileName", defaultValue = "data") String fileName) {
        try {
            return ResponseEntity.ok().header(DISPOSITION.getKey(), DISPOSITION.getContent().concat(fileName).concat(".csv"))
                    .body(new InputStreamResource(
                            csvService.generateCSV(jsonContentService.fetchJsonData(InternetProtocol.class, "InternetProtocol"), InternetProtocol.class)));
        } catch (Exception e) {
            return new ResponseEntity<>(wrapWithGenericResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/read-xlsx", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> readExcel(@RequestParam(value = "file") MultipartFile multipartFile) {
        try {
            return new ResponseEntity<>(wrapWithGenericResponse(xlsxService.read(multipartFile, InternetProtocol.class)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(wrapWithGenericResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/produce-xlsx", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> generateExcel(@RequestParam(value = "fileName") String fileName) {
        try {
            return ResponseEntity.ok().header(DISPOSITION.getKey(), DISPOSITION.getContent().concat(fileName).concat(".xlsx"))
                    .body(new InputStreamResource(xlsxService.write(jsonContentService.fetchJsonData(InternetProtocol.class, "InternetProtocol"))));
        } catch (Exception e) {
            return new ResponseEntity<>(wrapWithGenericResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/produce-pdf", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void generatePDF(@RequestParam(value = "fileName", defaultValue = "sample") String fileName, HttpServletResponse response) {
        try {
            response.setContentType("application/pdf");
            response.setHeader(DISPOSITION.getKey(), DISPOSITION.getContent().concat(fileName).concat(".pdf"));
            pdfService.write(response);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @GetMapping(value = "/produce-report", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<InputStreamResource> generatePDFReport(@RequestParam(value = "title", defaultValue = "IpData Report") String title) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                    reportService.generatePdfReport((List<?>) jsonContentService.fetchJsonData(InternetProtocol.class, "InternetProtocol").get(0), title));
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=report.pdf");
            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(new InputStreamResource(byteArrayInputStream));
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
