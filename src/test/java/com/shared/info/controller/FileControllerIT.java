package com.shared.info.controller;

import com.shared.info.SharedServicesApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        MvcResult actualResponse = mockMvc.perform(get("/file/fetch-content").param("fileName", "PDFContent.txt")).andDo(print()).andExpect(status().isOk()).andReturn();
        log.info("{}", actualResponse.getResponse().getBufferSize());
        assertEquals(HttpStatus.OK.value(), actualResponse.getResponse().getStatus());
    }

    @Test
    void should_parse_json_file_and_return_valid_response_when_file_name_is_passed() throws Exception {
        MvcResult actualResponse = mockMvc.perform(get("/file/fetch-json").param("stringPath", "InternetProtocol")).andDo(print()).andExpect(status().isOk()).andReturn();
        log.info("{}", actualResponse.getResponse().getBufferSize());
        assertEquals(HttpStatus.OK.value(), actualResponse.getResponse().getStatus());
    }

    @Test
    void should_parse_csv_file_and_return_valid_response_when_file_name_is_passed() throws Exception {
        MvcResult actualResponse = mockMvc.perform(multipart("/file/read-csv").file(multipartFile("MOCK_DATA_TEST.csv")).param("type", "ipdata")).andDo(print())
                .andExpect(status().isOk()).andReturn();

        log.info("{}", actualResponse.getResponse().getBufferSize());
        assertEquals(HttpStatus.OK.value(), actualResponse.getResponse().getStatus());
    }


    @Test
    void should_generate_csv_file_and_return_valid_response_when_file_name_is_passed() throws Exception {
        MvcResult actualResponse = mockMvc.perform(get("/file/produce-csv").param("fileName", "test")).andDo(print()).andExpect(status().isOk()).andReturn();
        log.info("{}", actualResponse.getResponse().getBufferSize());
        assertEquals(HttpStatus.OK.value(), actualResponse.getResponse().getStatus());
    }

    private static MockMultipartFile multipartFile(String path) throws IOException {
        File file = new File("src/main/resources/data/files/" + path);
        FileInputStream inputStream = new FileInputStream(file);
        return new MockMultipartFile("file", path, MediaType.MULTIPART_FORM_DATA_VALUE, inputStream);
    }

}
