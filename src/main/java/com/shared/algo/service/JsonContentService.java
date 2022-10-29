package com.shared.algo.service;

import java.util.Collection;

public interface JsonContentService {
	Collection<?> fetchJsonData(Class<?> clazz, String path) throws Exception;
}
