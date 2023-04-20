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
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ArrayManipulationsTest {

    @InjectMocks
    ArrayManipulations arrayManipulations;

    @Test
    @DisplayName("Summation of 2 array elements is target")
    void sumOfTwoIsTargetArray() {
        int[] inputArray = {2, 4, 3, 1, 6};
        int target = 7;
        List<Integer> expectedResult = Arrays.asList(1, 2);

        List<Integer> response = arrayManipulations.sumOfTwoIsTarget(inputArray, target);
        assertEquals(expectedResult, response);
    }

    @Test
    @DisplayName("Summation of 3 array elements is target")
    void sumOfThreeIsTargetArray() {
        int[] inputArray = {2, 4, 1, 3, 1, 2, 4, 1, 1, 3, 2};
        int target = 7;
        List<Integer> expectedResult = Arrays.asList(2, 4, 1);

        List<List<Integer>> response = arrayManipulations.sumOfThreeIsTarget(inputArray, target);
        assertEquals(expectedResult, response.get(0));
    }

    @Test
    @DisplayName("Smallest numbers whose digits product to give actual number")
    void smallestNumberForProduct() {
        int number = 1000;
        Integer response = arrayManipulations.smallestNumberForActualProduct(number);
        assertEquals(5558, response);
    }

    @Test
    @DisplayName("Print a 2D matrix spherically")
    void printMatrixSperically() {
        int[][] inputArray = {{2, 4, 1, 3},
                {3, 1, 2, 7},
                {4, 1, 1, 3},
                {5, 9, 8, 0}};
        arrayManipulations.printSperical(inputArray);
    }

    @Test
    @DisplayName("Separating Even And Odd Digits from array")
    void separateEvenAndOdd() {
        int[] expectedResponse = {4 ,4 ,6 ,8 ,2 ,1 ,7 ,3};
        int[] response = arrayManipulations.separateEvenAndOdd(array());
        for (int i : response) {
            System.out.print(i+" ");
        }
        assertEquals(expectedResponse[0], response[0]);
    }

    @Test
    @DisplayName("Extracting digits starting with 2 from the array")
    void extractNumberFromArray() {
        List<Integer> response = arrayManipulations.extractArrayElementsSpecifically(array());
        System.out.println(response);
    }

    @Test
    @DisplayName("Checks the occurrences of array elements")
    void checkOccurrences() {
        Map<Integer, Integer> response = arrayManipulations.checkOccurrences(array());
        assertEquals(2, response.get(4));
    }

    private int[] array() {
        return new int[]{3, 4, 6, 1, 2, 8, 7, 4};
    }
}