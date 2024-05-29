package com.shared.info.controller;

import com.shared.info.SharedServicesApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest(classes = SharedServicesApplication.class)
@AutoConfigureMockMvc(addFilters = false)
class FileControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_parse_text_file_and_return_valid_response_when_file_name_is_passed() throws Exception {
        var actualResponse = mockMvc.perform(get("/file/fetch-content").param("fileName", "PDFContent.txt"))
                .andDo(print()).andExpect(status().isOk()).andReturn();
        assertEquals(HttpStatus.OK.value(), actualResponse.getResponse().getStatus());
    }

    @Test
    void should_parse_json_file_and_return_valid_response_when_file_name_is_passed() throws Exception {
        var actualResponse = mockMvc.perform(get("/file/fetch-json").param("stringPath", "InternetProtocol"))
                .andDo(print()).andExpect(status().isOk()).andReturn();
        assertEquals(HttpStatus.OK.value(), actualResponse.getResponse().getStatus());
    }

    @Test
    void should_parse_csv_file_and_return_valid_response_when_file_name_is_passed() throws Exception {
        var actualResponse = mockMvc.perform(multipart("/file/read-csv").file(csvFile()).param("type", "ipdata"))
                .andDo(print()).andExpect(status().isOk()).andReturn();
        assertEquals(HttpStatus.OK.value(), actualResponse.getResponse().getStatus());
    }


    @Test
    void should_generate_csv_file_and_return_valid_response_when_file_name_is_passed() throws Exception {
        var actualResponse = mockMvc.perform(get("/file/produce-csv").param("fileName", "test")).andDo(print())
                .andExpect(status().isOk()).andReturn();
        assertEquals(HttpStatus.OK.value(), actualResponse.getResponse().getStatus());
    }

    @Test
    void should_parse_xlsx_file_and_return_valid_response_when_file_name_is_passed() throws Exception {
        MvcResult actualResponse = mockMvc.perform(multipart("/file/read-xlsx").file(xlsxFile())).andDo(print())
                .andExpect(status().isOk()).andReturn();
        assertEquals(HttpStatus.OK.value(), actualResponse.getResponse().getStatus());
    }

    @Test
    void should_generate_xlsx_file_and_return_valid_response_when_file_name_is_passed() throws Exception {
        var actualResponse = mockMvc.perform(get("/file/produce-xlsx").param("fileName", "test")).andDo(print())
                .andExpect(status().isOk()).andReturn();
        assertEquals(HttpStatus.OK.value(), actualResponse.getResponse().getStatus());
    }

    @Test
    void should_generate_pdf_file_and_return_valid_response_when_file_name_is_passed() throws Exception {
        var actualResponse = mockMvc.perform(get("/file/produce-pdf").param("fileName", "test")).andDo(print())
                .andExpect(status().isOk()).andReturn();
        assertEquals(HttpStatus.OK.value(), actualResponse.getResponse().getStatus());
    }

    @Test
    void should_generate_pdf_report_and_return_valid_response_when_file_name_is_passed() throws Exception {
        var actualResponse = mockMvc.perform(get("/file/produce-report").param("title", "test")).andDo(print())
                .andExpect(status().isOk()).andReturn();
        assertEquals(HttpStatus.OK.value(), actualResponse.getResponse().getStatus());
    }

    private static MockMultipartFile csvFile() throws IOException {
        var inputStream = new FileInputStream("src/main/resources/data/files/MOCK_DATA_TEST.csv");
        return new MockMultipartFile("file", "MOCK_DATA_TEST.csv", MULTIPART_FORM_DATA_VALUE, inputStream);
    }

    private static MockMultipartFile xlsxFile() throws IOException {
        var inputStream = new FileInputStream("src/main/resources/data/files/IpData_mock.xlsx");
        return new MockMultipartFile("file", "IpData_mock.xlsx", MULTIPART_FORM_DATA_VALUE, inputStream);
    }
}
