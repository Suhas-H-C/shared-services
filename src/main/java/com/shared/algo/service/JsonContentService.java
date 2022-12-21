package com.shared.algo.service;

import java.util.List;

@FunctionalInterface
public interface JsonContentService {
    List<?> fetchJsonData(Class<?> clazz, String path) throws Exception;
}
