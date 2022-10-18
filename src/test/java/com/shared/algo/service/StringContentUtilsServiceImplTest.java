package com.shared.algo.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StringContentUtilsServiceImplTest {

    @InjectMocks
    private StringContentUtilsServiceImpl stringContentUtilsImpl;

    @Test
    @DisplayName("testGetContent_IllegalArgumentException")
    void testGetContent() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            stringContentUtilsImpl.getContent("demo");
        });
    }

    @Test
    void testGetFields() {
    }

}
