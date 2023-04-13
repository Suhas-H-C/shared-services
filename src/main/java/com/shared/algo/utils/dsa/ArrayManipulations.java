package com.shared.algo.utils.dsa;

import com.shared.algo.exception.ClassTypeNotSupportedException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Scope("singleton")
public class ArrayManipulations {

    public List<Integer> sumOfTwoIsTarget(int[] arr, int target) throws ClassTypeNotSupportedException {
        Map<Integer, Integer> checker = new HashMap<>();
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            int newTarget = target - arr[i];
            if (checker.containsKey(newTarget)) {
                res.add(checker.get(newTarget));
                res.add(i);
                return res;
            }
            checker.put(arr[i], i);
        }
        return res;
    }
}
