package com.shared.info.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.Collection;
import java.util.List;

public interface XlsxService {
    Collection<?> read(MultipartFile multipartFile, Class<?> clazz) throws Exception;
    ByteArrayInputStream write(List<?> data) throws Exception;
}
