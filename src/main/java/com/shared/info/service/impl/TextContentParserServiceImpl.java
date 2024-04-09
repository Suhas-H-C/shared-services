package com.shared.info.service.impl;

import com.shared.info.enums.Messages;
import com.shared.info.exception.BadRequestException;
import com.shared.info.annotations.Header;
import com.shared.info.service.TextContentParserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.nonNull;

@Slf4j
@Service
public final class TextContentParserServiceImpl implements TextContentParserService {

    @Override
    public String parseTextContent(String fileName) throws RuntimeException {
        log.info("fileName : {}", fileName);
        InputStream in = getClass().getClassLoader().getResourceAsStream("data/files/".concat(fileName));

        if (nonNull(in)) {
            StringBuilder sb = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(in), StandardCharsets.UTF_8))) {
                int c;
                while ((c = reader.read()) != -1) {
                    sb.append((char) c);
                }
                return sb.toString();
            } catch (Exception e) {
                throw new BadRequestException(Messages.FILE_READING_FAILED.getMessage());
            }
        } else {
            throw new NullPointerException(Messages.INVALID_FILE_NAME.getMessage());
        }
    }

    @Override
    public Collection<?> getClassFieldsAsString(Class<?> clazz) {
        List<String> list = new ArrayList<>();
        if (clazz.toString().contains("InternetProtocol")) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                Header config = field.getAnnotation(Header.class);
                log.info(config.header());
                list.add(config.header());
            }
        }
        return list;
    }
}
