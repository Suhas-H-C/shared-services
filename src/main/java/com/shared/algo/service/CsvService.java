package com.shared.algo.service;

import java.io.ByteArrayInputStream;
import java.util.Collection;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface CsvService {

	Collection<?> retrieveData(MultipartFile multipartFile, String contentType) throws Exception;

	ByteArrayInputStream getCSV(List<?> data, Class<?> clazz);
}
