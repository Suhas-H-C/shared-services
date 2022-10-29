package com.shared.algo.service;

import java.util.Collection;

public interface StringContentUtilsService {

	Collection<?> getFields(Object obj);

	String getContent(String fileName) throws Exception;
}
