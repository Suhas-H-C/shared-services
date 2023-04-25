package com.shared.algo.service;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Collection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shared.algo.model.InternetProtocol;

@ExtendWith(MockitoExtension.class)
class JsonContentServiceImplTest {

	@InjectMocks
	private JsonContentServiceImpl jsonContentServiceImpl;

	@Mock
	private ObjectMapper objectMapper;

	@Test
	@DisplayName("readJsonContent")
	void readJsonContent() throws Exception {
		Collection<?> data = jsonContentServiceImpl.fetchJsonData(InternetProtocol.class, "InternetProtocol");
		assertFalse(data.isEmpty());
	}

}
