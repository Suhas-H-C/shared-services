package com.shared.info.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shared.info.dto.InternetProtocol;
import com.shared.info.exception.BadRequestException;
import com.shared.info.service.JsonContentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.Objects;

import static com.shared.info.enums.Messages.TYPE_NOT_FOUND;

@Slf4j
@Service
public final class JsonContentServiceImpl implements JsonContentService {

    @Override
    public List<?> fetchJsonData(Class<?> clazz, String path) throws Exception {
        if (clazz.isInstance(new InternetProtocol()) && Objects.nonNull(path)) {
            TypeReference<List<InternetProtocol>> data = new TypeReference<>() {
            };
            Object jsonResponse = processJsonRequest(data, path);
            log.info("Processing completed");
            return (List<?>) jsonResponse;
        }
        throw new BadRequestException(TYPE_NOT_FOUND.getMessage());
    }

    private static Object processJsonRequest(TypeReference<?> typeReference, String path) throws Exception {
        InputStream in = TypeReference.class.getResourceAsStream("/data/json/" + path + ".json");

        ObjectMapper objectMapper = BeanUtils.instantiateClass(ObjectMapper.class);
        return objectMapper.readValue(in, typeReference);
    }

}
