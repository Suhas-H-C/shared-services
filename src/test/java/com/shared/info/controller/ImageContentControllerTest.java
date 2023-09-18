package com.shared.info.controller;

import com.shared.info.service.ImageContentService;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ImageContentControllerTest {

    private final ImageContentService service = mock(ImageContentService.class);
    private final ImageContentController controller = new ImageContentController(service);

    @Test
    void should_return_string_response_when_language_selected_is_eng() throws Exception {
        File file = new File("src/main/resources/data/files/IpData_mock.xlsx");
        FileInputStream fileInputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(), fileInputStream);

        when(service.processImage(multipartFile, "eng")).thenReturn("Image processing successful");

        String actualResponse = controller.processImage(multipartFile, "eng");
        assertEquals("Image processing successful", actualResponse);
        assertNotNull(actualResponse);
        verify(service).processImage(multipartFile, "eng");
    }
}
