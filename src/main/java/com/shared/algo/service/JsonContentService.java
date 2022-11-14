package com.shared.algo.service;

import java.util.List;

public interface JsonContentService {
	List<?> fetchJsonData(Class<?> clazz, String path) throws Exception;
}
