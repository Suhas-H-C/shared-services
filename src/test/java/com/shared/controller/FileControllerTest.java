package com.shared.controller;

import com.shared.algo.controller.FileController;
import com.shared.algo.model.InternetProtocol;
import com.shared.algo.service.*;
import com.shared.algo.utils.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Collections;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Slf4j
class FileControllerTest {

    private final TextContentParserService textContentParserService = mock(TextContentParserService.class);
    private final JsonContentService jsonContentService = mock(JsonContentService.class);
    private final CsvService csvService = mock(CsvService.class);
    private final XlsxService xlsxService = mock(XlsxService.class);
    private final ReportService reportService = mock(ReportService.class);

    private final FileController controller = new FileController(textContentParserService, jsonContentService, csvService, xlsxService, null, reportService);

    @Test
    void should_parse_the_file_and_return_string_when_file_name_is_provided() {
        when(textContentParserService.parseTextContent("test")).thenReturn("Content parsed successfully");
        ResponseEntity<GenericResponse<?>> response = controller.getFileContentAsString("test");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(requireNonNull(response.getBody()).data());
        assertTrue(response.getBody().metaData().success());
    }

    @Test
    void should_throw_null_pointer_when_incorrect_file_name_is_provided() {
        when(textContentParserService.parseTextContent("test")).thenThrow(NullPointerException.class);
        ResponseEntity<GenericResponse<?>> response = controller.getFileContentAsString("test");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertFalse(requireNonNull(response.getBody()).metaData().success());
    }

    @Test
    void should_fetch_json_date_when_file_name_is_provided() throws Exception {
        when(jsonContentService.fetchJsonData(InternetProtocol.class, "test")).thenReturn(Collections.emptyList());

        ResponseEntity<GenericResponse<?>> response = controller.getJsonFromFile("test");
        log.info("API status : {}", requireNonNull(response.getBody()).metaData().success());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(requireNonNull(response.getBody()).errors().isSuccess());
    }

    @Test
    void should_fetch_csv_date_when_file_name_is_provided() throws Exception {
        when(csvService.readCSVFileContent(multipartFile("MOCK_DATA_TEST.csv"), "ipdata")).thenReturn(Collections.emptyList());
        ResponseEntity<GenericResponse<?>> response = controller.readCSV(multipartFile("MOCK_DATA_TEST.csv"), "ipdata");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(requireNonNull(response.getBody()).data());
        assertFalse(response.getBody().metaData().success());
    }

    @Test
    void should_generate_csv_file_when_data_is_provided() throws Exception {
        when(jsonContentService.fetchJsonData(InternetProtocol.class, "InternetProtocol")).thenReturn(List.of());
        when(csvService.generateCSV(jsonContentService.fetchJsonData(InternetProtocol.class, "InternetProtocol"), InternetProtocol.class))
                .thenReturn(new ByteArrayInputStream(new ByteArrayOutputStream().toByteArray()));

        ResponseEntity<?> response = controller.generateCSV("Sample File");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void should_read_xlsx_file_when_file_name_is_provided() throws Exception {
        when(xlsxService.read(multipartFile("IpData_mock.xlsx"), InternetProtocol.class)).thenReturn(Collections.emptyList());
        ResponseEntity<?> response = controller.readExcel(multipartFile("IpData_mock.xlsx"));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.hasBody());
    }

    @Test
    void should_generate_xlsx_file_when_data_is_provided() throws Exception {
        when(jsonContentService.fetchJsonData(InternetProtocol.class, "InternetProtocol")).thenReturn(List.of());
        when(xlsxService.write(jsonContentService.fetchJsonData(InternetProtocol.class, "InternetProtocol")))
                .thenReturn(new ByteArrayInputStream(new ByteArrayOutputStream().toByteArray()));

        ResponseEntity<?> response = controller.generateExcel("sample");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    private MultipartFile multipartFile(String fileName) throws IOException {
        File file = new File("src/main/resources/data/files/" + fileName);
        FileInputStream fileInputStream = new FileInputStream(file);

        return new MockMultipartFile(file.getName(), fileInputStream);
    }
}
