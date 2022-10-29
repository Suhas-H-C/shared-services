package com.shared.algo.service;

import java.io.ByteArrayInputStream;
import java.util.Collection;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface XlsxService {

	Collection<?> read(MultipartFile multipartFile, Class<?> clazz) throws Exception;
	ByteArrayInputStream write(List<?> data) throws Exception;
}
