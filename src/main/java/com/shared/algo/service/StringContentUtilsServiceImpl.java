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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.shared.algo.annotations.FiledHeaderConfig;
import static com.shared.algo.enums.Messages.*;
import com.shared.algo.exception.BadRequestException;
import com.shared.algo.model.IpData;

@Service
public final class StringContentUtilsServiceImpl implements StringContentUtilsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StringContentUtilsServiceImpl.class);

	@Override
	public String getContent(String fileName) throws Exception {
		LOGGER.info("fileName : {}", fileName);
		InputStream in = getClass().getClassLoader().getResourceAsStream("data/files/".concat(fileName));

		if (nonNull(in)) {
			StringBuilder sb = new StringBuilder();

			try (BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(in),
					Charset.forName(StandardCharsets.UTF_8.name())))) {
				int c;
				while ((c = reader.read()) != -1) {
					sb.append((char) c);
				}
				return sb.toString();
			} catch (Exception e) {
				throw new BadRequestException(FILE_READING_FAILED.getMessage());
			}
		} else {
			throw new NullPointerException(INVALID_FILE_NAME.getMessage());
		}
	}

	@Override
	public Collection<?> getFields(Object obj) {
		List<String> list = new ArrayList<>();

		if (obj instanceof IpData data) {
			Field[] fields = data.getClass().getDeclaredFields();
			for (Field field : fields) {
				list.add(field.getAnnotation(FiledHeaderConfig.class).header());
			}
			return list;
		}
		return list;
	}
}
