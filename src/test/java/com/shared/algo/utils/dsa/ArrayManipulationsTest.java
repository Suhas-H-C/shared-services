package com.shared.algo.utils.dsa;

import io.cucumber.java.sl.In;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ArrayManipulationsTest {

    @InjectMocks
    ArrayManipulations arrayManipulations;

    @Test
    @DisplayName("ArrayCheckTarget")
    void sumOfTwoIsTargetArray() {
        int[] inputArray = {2, 4, 3, 1, 6};
        int target = 7;
        List<Integer> expectedResult = Arrays.asList(1, 2);

        List<Integer> response = arrayManipulations.sumOfTwoIsTarget(inputArray, target);
        assertEquals(expectedResult, response);
    }

    @Test
    @DisplayName("ListCheckTarget")
    void sumOfTwoIsTargetList() {
        List<Integer> inputArray = Arrays.asList(2, 4, 3, 1, 6);
        int target = 7;
        List<Integer> expectedResult = Arrays.asList(1, 2);

        List<Integer> response = arrayManipulations.sumOfTwoIsTarget(inputArray, target);
        assertEquals(expectedResult, response);
    }
}