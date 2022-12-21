package com.shared.algo.service;

import org.springframework.web.multipart.MultipartFile;

@FunctionalInterface
public interface ImgContentService {

    String processImage(MultipartFile multipartFile, String lang) throws Exception;
}
