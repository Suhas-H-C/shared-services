package com.shared.algo.controller;

import com.shared.algo.controller.documentation.ImageContentControllerDocumentation;
import com.shared.algo.service.ImageContentService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/img")
public final class ImageContentController implements ImageContentControllerDocumentation {

    private final ImageContentService imgContentService;

    @PostMapping(value = "/process", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String processImage(@RequestParam(name = "image") MultipartFile image, @RequestParam(name = "lang") String lang) throws Exception {
        return imgContentService.processImage(image, lang);
    }
}
