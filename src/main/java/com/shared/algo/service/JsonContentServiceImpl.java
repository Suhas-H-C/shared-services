package com.shared.algo.service;

import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import static com.shared.algo.enums.Messages.TYPE_NOT_FOUND;
import com.shared.algo.exception.BadRequestException;
import com.shared.algo.model.IpData;

@Service
public class JsonContentServiceImpl implements JsonContentService {

	private static final Logger LOGGER = LoggerFactory.getLogger(JsonContentServiceImpl.class);

	@Override
	public Collection<?> fetchJsonData(Class<?> clazz, String path) throws Exception {
		
		if(clazz.isInstance(new IpData()) && Objects.nonNull(path)) {
			TypeReference<List<IpData>> data = new TypeReference<>() {
			};
			Object jsonResponse = processJsonRequest(data, path);
			LOGGER.info("Processing completed");
			return Collections.singleton(jsonResponse);
		}
		throw new BadRequestException(TYPE_NOT_FOUND.getMessage());
	}

	private static final Object processJsonRequest(TypeReference<?> typeReference, String path) throws Exception {
		InputStream in = TypeReference.class.getResourceAsStream("/data/json/" + path + ".json");

		ObjectMapper objectMapper = BeanUtils.instantiateClass(ObjectMapper.class);
		Object value = objectMapper.readValue(in, typeReference);
		return value;
	}

}
