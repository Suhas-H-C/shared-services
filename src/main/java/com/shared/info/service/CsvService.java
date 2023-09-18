package com.shared.info.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.Collection;
import java.util.List;

public interface CsvService {

    Collection<?> readCSVFileContent(MultipartFile multipartFile, String contentType) throws Exception;
    ByteArrayInputStream generateCSV(List<?> data, Class<?> clazz);
}
