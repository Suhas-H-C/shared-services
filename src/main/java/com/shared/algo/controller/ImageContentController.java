package com.shared.algo.controller;

import com.shared.algo.controller.documentation.ImageContentControllerDocumentation;
import com.shared.algo.service.ImageContentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/img")
public final class ImageContentController implements ImageContentControllerDocumentation {

    private final ImageContentService service;

    @PostMapping(value = "/process", consumes = MULTIPART_FORM_DATA_VALUE, produces = APPLICATION_JSON_VALUE)
    public String processImage(@RequestParam(name = "image") MultipartFile image, @RequestParam(name = "lang") String lang) throws Exception {
        return service.processImage(image, lang);
    }
}
