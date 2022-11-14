package com.shared.algo.service;

import java.util.Collection;

public interface StringContentUtilsService {

	Collection<?> getFields(Class<?> clazz);

	String getContent(String fileName) throws Exception;
}
