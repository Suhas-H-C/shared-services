package com.shared.algo.service;

import java.io.InputStream;
import java.util.Collection;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class JsonContentServiceImpl implements JsonContentService {

	@Override
	public Object fetchJsonData(Class<?> clazz, String path) throws Exception {
		TypeReference<Collection<?>> data = new TypeReference<Collection<?>>() {};
		InputStream in = 
				TypeReference.class.getResourceAsStream("data/json/" + path + ".json");
		
		ObjectMapper objectMapper = new ObjectMapper();
		Collection<?> value = objectMapper.readValue(in, data);
		return value;
	}

}
