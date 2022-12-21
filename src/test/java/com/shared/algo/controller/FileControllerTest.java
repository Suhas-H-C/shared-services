package com.shared.algo.controller;

import com.shared.algo.service.*;
import com.shared.algo.utils.GenericResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Collections;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FileControllerTest {

    private static final Logger LOG = LoggerFactory.getLogger(FileControllerTest.class);

    @InjectMocks
    private FileController fileController;

    @Mock
    private StringContentUtilsService stringContentUtils;
    @Mock
    private JsonContentService jsonContentService;
    @Mock
    private CsvService csvService;
    @Mock
    private XlsxService xlsxService;
    @Mock
    private ReportService reportService;

    @Test
    @DisplayName("Get content success")
    void testGetContents() throws Exception {
        when(stringContentUtils.getContent(anyString()))
                .thenReturn("Content parsed successfully");

        ResponseEntity<GenericResponse<?>> response = fileController.getContent("test");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(requireNonNull(response.getBody()).getData());
        assertTrue(response.getBody().getMetaData().isSuccess());
        reset(stringContentUtils);
    }

    @Test
    @DisplayName("Get content failure")
    void testGetContentsBadRequest() throws Exception {
        when(stringContentUtils.getContent(anyString()))
                .thenThrow(NullPointerException.class);

        ResponseEntity<GenericResponse<?>> response = fileController.getContent("test");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertFalse(requireNonNull(response.getBody()).getMetaData().isSuccess());
        reset(stringContentUtils);
    }

    @Test
    @DisplayName("Get Json success")
    void testGetJsonSuccess() throws Exception {
        when(jsonContentService.fetchJsonData(any(), anyString()))
                .thenReturn(Collections.emptyList());

        ResponseEntity<GenericResponse<?>> response = fileController.getJson("test");
        LOG.info("API status : {}", requireNonNull(response.getBody()).getMetaData().isSuccess());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(requireNonNull(response.getBody()).getErrors().isSuccess());
        reset(jsonContentService);
    }

    @Test
    @DisplayName("Get Json failure")
    void testGetJsonFailure() throws Exception {
        when(jsonContentService.fetchJsonData(any(), anyString()))
                .thenThrow(NullPointerException.class);

        ResponseEntity<GenericResponse<?>> response = fileController.getJson("test");
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        reset(jsonContentService);
    }

    @Test
    @DisplayName("Read CSV")
    void testReadCsv() throws Exception {
        when(csvService.retrieveData(any(), anyString()))
                .thenReturn(Collections.emptyList());

        ResponseEntity<GenericResponse<?>> response = fileController.readCSV(multipartFile("MOCK_DATA_TEST.csv"), "ipdata");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(requireNonNull(response.getBody()).getData());
        assertFalse(response.getBody().getMetaData().isSuccess());
        reset(csvService);
    }

    @Test
    @DisplayName("Read CSV BAD REQUEST")
    void testReadCsvBadRequest() throws Exception {
        when(csvService.retrieveData(any(), anyString()))
                .thenThrow(NullPointerException.class);

        ResponseEntity<GenericResponse<?>> response = fileController.readCSV(multipartFile("MOCK_DATA_TEST.csv"), "ipdata");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertFalse(requireNonNull(response.getBody()).getMetaData().isSuccess());
        reset(csvService);
    }

    @Test
    @DisplayName("Get CSV Success")
    void testGetCSV() {
        when(csvService.getCSV(any(), any()))
                .thenReturn(new ByteArrayInputStream(new ByteArrayOutputStream().toByteArray()));

        ResponseEntity<?> response = fileController.getCSV("Sample File");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        reset(csvService);
    }

    @Test
    @DisplayName("Get CSV BAD REQUEST")
    void testGetCSVBadRequest() throws Exception {
        when(csvService.retrieveData(any(), anyString()))
                .thenThrow(NullPointerException.class);

        ResponseEntity<?> response = fileController.getCSV("Sample File");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        reset(csvService);
    }

    @Test
    @DisplayName("Read XLSX success")
    void testReadXlsx() throws Exception {
        when(xlsxService.read(any(), any()))
                .thenReturn(Collections.emptyList());

        ResponseEntity<?> response = fileController.readExcel(multipartFile("IpData_mock.xlsx"));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.hasBody());
        reset(xlsxService);
    }

    @Test
    @DisplayName("Read XLSX failure")
    void testReadXlsxBadRequest() throws Exception {
        when(xlsxService.read(any(), any()))
                .thenThrow(NullPointerException.class);

        ResponseEntity<?> response = fileController.readExcel(multipartFile("IpData_mock.xlsx"));

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        reset(xlsxService);
    }


    @Test
    @DisplayName("Get XLSX success")
    void testGetXlsx() throws Exception {
        when(xlsxService.write(any()))
                .thenReturn(new ByteArrayInputStream(new ByteArrayOutputStream().toByteArray()));

        ResponseEntity<?> response = fileController.getExcel("sample");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        reset(xlsxService);
    }

    @Test
    @DisplayName("Get PDF report Exception")
    void getPDFReportException() throws Exception {
        when(reportService.generatePdfReport(any(), anyString()))
                .thenThrow(NullPointerException.class);

        assertThrows(Exception.class, () -> {
            fileController.getPDFReport("tester sample");
        });
        reset(reportService);
    }

    private MultipartFile multipartFile(String fileName) throws IOException {
        File file = new File("src/main/resources/data/files/" + fileName);
        FileInputStream fileInputStream = new FileInputStream(file);

        return new MockMultipartFile(file.getName(), fileInputStream);
    }
}
