package com.shared.algo.utils.dsa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class IntegerManipulationTest {

    @InjectMocks
    IntegerManipulation integerManipulation;

    @Test
    void printSquareRootsTill() {
        List<Integer> response =
                integerManipulation.printSquareRootsTill(20);

        assertEquals(16,response.get(response.size() - 1));
    }
}