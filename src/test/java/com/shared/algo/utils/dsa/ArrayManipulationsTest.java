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
    @DisplayName("ArrayCheck2Target")
    void sumOfTwoIsTargetArray() {
        int[] inputArray = {2, 4, 3, 1, 6};
        int target = 7;
        List<Integer> expectedResult = Arrays.asList(1, 2);

        List<Integer> response = arrayManipulations.sumOfTwoIsTarget(inputArray, target);
        assertEquals(expectedResult, response);
    }

    @Test
    @DisplayName("ArrayCheck3Target")
    void sumOfThreeIsTargetArray() {
        int[] inputArray = {2, 4, 1, 3, 1, 2, 4, 1, 1, 3, 2};
        int target = 7;
        List<Integer> expectedResult = Arrays.asList(2, 4, 1);

        List<List<Integer>> response = arrayManipulations.sumOfThreeIsTarget(inputArray, target);
        assertEquals(expectedResult, response.get(0));
    }

    @Test
    @DisplayName("SmallestNumberThatProductsActualNumber")
    void smallestNumberForProduct() {
        int number = 1000;
        Integer response = arrayManipulations.smallestNumberForActualProduct(number);
        assertEquals(5558, response);
    }

    @Test
    @DisplayName("SpericalPriting")
    void printMatrixSperically() {
        int[][] inputArray = {{2, 4, 1, 3},
                {3, 1, 2, 7},
                {4, 1, 1, 3},
                {5, 9, 8, 0}};
        arrayManipulations.printSperical(inputArray);
    }
}