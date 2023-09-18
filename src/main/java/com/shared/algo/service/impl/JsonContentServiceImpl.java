package com.shared.algo.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shared.algo.exception.BadRequestException;
import com.shared.algo.model.InternetProtocol;
import com.shared.algo.service.JsonContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.Objects;

import static com.shared.algo.enums.Messages.TYPE_NOT_FOUND;

@Service
public final class JsonContentServiceImpl implements JsonContentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonContentServiceImpl.class);

    @Override
    public List<?> fetchJsonData(Class<?> clazz, String path) throws Exception {

        if (clazz.isInstance(new InternetProtocol()) && Objects.nonNull(path)) {
            TypeReference<List<InternetProtocol>> data = new TypeReference<>() {
            };
            Object jsonResponse = processJsonRequest(data, path);
            LOGGER.info("Processing completed");
            return List.of(jsonResponse);
        }
        throw new BadRequestException(TYPE_NOT_FOUND.getMessage());
    }

    private static Object processJsonRequest(TypeReference<?> typeReference, String path) throws Exception {
        InputStream in = TypeReference.class.getResourceAsStream("/data/json/" + path + ".json");

        ObjectMapper objectMapper = BeanUtils.instantiateClass(ObjectMapper.class);
        Object value = objectMapper.readValue(in, typeReference);
        return value;
    }

}
