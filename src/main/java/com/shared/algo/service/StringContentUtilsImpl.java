package com.shared.algo.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import javax.management.BadStringOperationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StringContentUtilsImpl implements StringContentUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(StringContentUtilsImpl.class);

	@Override
	public String getContent(String fileName) throws Exception {
		LOGGER.info("fileName : {}", fileName);
		InputStream in = getClass().getClassLoader().getResourceAsStream("data/files/".concat(fileName));

		if(Objects.nonNull(in)) {
			StringBuilder sb = new StringBuilder();

			try (BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(in), Charset.forName(StandardCharsets.UTF_8.name())))){
				int c;
				while((c = reader.read()) != -1) {
					sb.append((char)c);
				}
				LOGGER.info("Read successful !");
				return sb.toString();
			} catch (Exception e) {
				throw new BadStringOperationException("Error encountered");
			}
		}else {
			throw new IllegalArgumentException("Please check the file name");
		}
	}
}
