package com.shared.algo.service;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import org.apache.commons.csv.CSVParser;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;

@ExtendWith(MockitoExtension.class)
class CsvServiceImplTest {

	
	@InjectMocks
	private CsvServiceImpl csvServiceImpl;
	
	
	@Test
	@DisplayName("readCsvFileTest")
	void readCsvFileTest() throws Exception {
		InputStream in = TypeReference.class.getResourceAsStream("/data/files/MOCK_DATA_TEST.csv");
		
		MockMultipartFile multipartFile = new MockMultipartFile("test", in);
		Collection<?> data = csvServiceImpl.retrieveData(multipartFile);
		
		assertFalse(data.isEmpty());
		assertEquals(10, data.size());
	}

}