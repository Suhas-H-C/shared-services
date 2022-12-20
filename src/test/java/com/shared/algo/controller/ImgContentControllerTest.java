package com.shared.algo.controller;

import com.shared.algo.service.ImgContentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImgContentControllerTest {

    @InjectMocks
    private ImgContentController imgContentController;

    @Mock
    private ImgContentService imgContentService;

    @Test
    @DisplayName("Test tesseract4j OCR")
    void testOcr() throws Exception {
        when(imgContentService.processImage(any(), anyString()))
                .thenReturn("Image processing successful");

        String response = imgContentController.processImage(multipartFile(), "en");

        assertEquals("Image processing successful", response);
        assertNotNull(response);

        reset(imgContentService);
    }

    private MultipartFile multipartFile() throws IOException {
        File file = new File("src/main/resources/data/files/IpData_mock.xlsx");
        FileInputStream fileInputStream = new FileInputStream(file);
        return new MockMultipartFile(file.getName(), fileInputStream);
    }

}
