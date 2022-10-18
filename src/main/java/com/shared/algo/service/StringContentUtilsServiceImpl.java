package com.shared.algo.service;

import static java.util.Objects.nonNull;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.management.BadStringOperationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.shared.algo.annotations.Headers;
import com.shared.algo.model.IpData;

@Service
public class StringContentUtilsServiceImpl implements StringContentUtilsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StringContentUtilsServiceImpl.class);

    @Override
    public String getContent(String fileName) throws Exception {
        LOGGER.info("fileName : {}", fileName);
        InputStream in = getClass().getClassLoader().getResourceAsStream("data/files/".concat(fileName));

        if (nonNull(in)) {
            StringBuilder sb = new StringBuilder();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(in), Charset.forName(StandardCharsets.UTF_8.name())))) {
                int c;
                while ((c = reader.read()) != -1) {
                    sb.append((char) c);
                }
                LOGGER.info("Read successful !");
                return sb.toString();
            } catch (Exception e) {
                throw new BadStringOperationException("Error encountered");
            }
        } else {
            throw new IllegalArgumentException("Please check the file name");
        }
    }

    @Override
    public Collection<?> getFields(Object obj) {
        List<String> list = new ArrayList<>();

        if (obj instanceof IpData data) {
            Field[] fields = data.getClass().getDeclaredFields();
            for (Field field : fields) {
                list.add(field.getAnnotation(Headers.class).header());
            }
            return list;
        }
        return list;
    }
}
