package com.shared.algo.service;

import java.util.Collection;

public interface TextContentParserService {

    Collection<?> getClassFieldsAsString(Class<?> clazz);
    String parseTextContent(String fileName) throws RuntimeException;
}
