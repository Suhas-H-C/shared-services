package com.shared.info.service;

import org.springframework.web.multipart.MultipartFile;

@FunctionalInterface
public interface ImageContentService {
    String processImage(MultipartFile multipartFile, String lang);
}
