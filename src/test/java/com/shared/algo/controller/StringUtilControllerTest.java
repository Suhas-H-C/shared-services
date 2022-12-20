package com.shared.algo.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.shared.algo.service.StringContentUtilsService;
import com.shared.algo.utils.GenericResponse;

@ExtendWith(MockitoExtension.class)
class StringUtilControllerTest {

	@InjectMocks
	private StringUtilController stringUtilController;
	@Mock
	private StringContentUtilsService stringContentUtils;
	
	
	@Test
	@DisplayName("Test field response")
	void testFieldsResponse() {
		when(stringContentUtils.getFields(any()))
		.thenReturn(null);
		
		ResponseEntity<GenericResponse<?>> response = stringUtilController.getFields();
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(response.getBody().getData().isEmpty());
		reset(stringContentUtils);
	}
	
}
