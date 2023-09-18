package com.shared.info.dsa;

import com.shared.info.utils.dsa.BinarySearch;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class BinarySearchTest {

    @InjectMocks
    private BinarySearch binarySearch;

    @Test
    void searchForTargetFirstOcc() {
        int response = binarySearch.searchForTargetFirstOcc(array(), 5);
        assertEquals(6, response);
    }

    @Test
    void searchForTargetLastOcc() {
        int response = binarySearch.searchForTargetLastOcc(array(), 2);
        assertEquals(2, response);
    }

    @Test
    void searchForTargetFirstAndLastOcc() {
        binarySearch.searchForTargetFirstAndLastOcc(array(), 5);
    }

    private int[] array() {
        int[] array = {3, 4, 5, 2, 1, 2, 4};
        Arrays.sort(array);
        return array;
    }
}