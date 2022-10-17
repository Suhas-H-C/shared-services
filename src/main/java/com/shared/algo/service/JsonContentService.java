package com.shared.algo.service;

public interface JsonContentService {

	Object fetchJsonData(Class<?> clazz, String path) throws Exception;
}
