package com.shared.algo.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.Collection;
import java.util.List;

public interface CsvService {

    Collection<?> retrieveData(MultipartFile multipartFile, String contentType) throws Exception;

    ByteArrayInputStream getCSV(List<?> data, Class<?> clazz);
}
