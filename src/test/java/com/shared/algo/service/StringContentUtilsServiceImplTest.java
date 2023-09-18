package com.shared.algo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;

import com.shared.algo.service.impl.TextContentParserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.shared.algo.model.InternetProtocol;

@ExtendWith(MockitoExtension.class)
class StringContentUtilsServiceImplTest {

	@InjectMocks
	private TextContentParserServiceImpl stringContentUtilsImpl;

	@Test
	@DisplayName("testGetContent_IllegalArgumentException")
	void testGetContent() {
		assertThrows(NullPointerException.class, () -> {
			stringContentUtilsImpl.parseTextContent("demo");
		});
	}

	@Test
	@DisplayName("testGetFields_False")
	void testGetFields() {
		Collection<?> feildData = stringContentUtilsImpl.getClassFieldsAsString(new InternetProtocol().getClass());
		assertTrue(feildData.isEmpty());
	}

}
