package com.shared.algo.controller;

import com.shared.algo.service.ImgContentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/img")
public final class ImgContentController {

    private static final Logger LOG = LoggerFactory.getLogger(ImgContentController.class);

    @Autowired
    private ImgContentService imgContentService;

    @Operation(method = "POST", description = "Extracts Image text contents", tags = "file")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @PostMapping(value = "/process", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String processImage(@RequestParam(name = "image") MultipartFile image,
                               @RequestParam(name = "lang") String lang) throws Exception {
        String response = imgContentService.processImage(image, lang);
        LOG.info("ocr response : {}", response);
        return response;
    }

}
