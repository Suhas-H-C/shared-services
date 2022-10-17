package com.shared.algo.service;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shared.algo.model.IpData;

@Service
public class JsonContentServiceImpl implements JsonContentService {

	private static final Logger LOGGER = LoggerFactory.getLogger(JsonContentServiceImpl.class);
	
	@Override
	public Collection<?> fetchJsonData(Class<?> clazz, String path) throws Exception {
		TypeReference<List<IpData>> data = new TypeReference<List<IpData>>() {};
		InputStream in = 
				TypeReference.class.getResourceAsStream("/data/json/" + path + ".json");
		
		ObjectMapper objectMapper = new ObjectMapper();
		List<IpData> value = objectMapper.readValue(in, data);
		LOGGER.info("Data size : {}", value.size());
		return value;
	}

}
