package com.shared.algo.service;

import java.util.Collection;

import org.springframework.web.multipart.MultipartFile;

public interface CsvService {

	Collection<?> retrieveData(MultipartFile multipartFile) throws Exception;
}
