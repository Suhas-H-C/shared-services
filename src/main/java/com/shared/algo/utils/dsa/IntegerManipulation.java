package com.shared.algo.utils.dsa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class IntegerManipulation {

    private static final Logger LOGGER = LoggerFactory.getLogger(IntegerManipulation.class);

    public List<Integer> printSquareRootsTill(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int temp = i * i;
            if (temp != 0 && temp < n) {
                LOGGER.info("{}", temp);
                res.add(temp);
            }
        }
        return res;
    }

}
