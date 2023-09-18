package com.shared.info.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.shared.info.service.impl.CsvServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.InputStream;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CsvServiceImplTest {

    private final CsvServiceImpl service = new CsvServiceImpl();

    @Test
    void should_parse_csv_file_and_return_the_content_when_file_nane_is_passed() throws Exception {
        InputStream in = TypeReference.class.getResourceAsStream("/data/files/MOCK_DATA_TEST.csv");

        MockMultipartFile multipartFile = new MockMultipartFile("test", in);
        Collection<?> actualResponse = service.readCSVFileContent(multipartFile, "ipdata");

        assertFalse(actualResponse.isEmpty());
        assertEquals(10, actualResponse.size());
    }

}
