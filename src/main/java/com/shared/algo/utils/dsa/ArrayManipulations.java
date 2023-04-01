package com.shared.algo.utils.dsa;

import com.shared.algo.exception.ClassTypeNotSupportedException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Scope("singleton")
public class ArrayManipulations {

    public <T> List<Integer> sumOfTwoIsTarget(Object list, T target) throws ClassTypeNotSupportedException {
        Map<Integer, Integer> checker = new HashMap<>();
        List<Integer> res = new ArrayList<>();

        if (list instanceof List<?>) {
            return checkOfTarget((List<Integer>) list, target, checker, res);
        } else if (list instanceof int[] ints) {
            return checkOfTarget(ints, target, checker, res);
        } else {
            throw new ClassTypeNotSupportedException("Supported for arrays/list of Integers only");
        }
    }


    private static <T> List<Integer> checkOfTarget(List<Integer> listOfIntegers, T target, Map<Integer, Integer> checker, List<Integer> res) {
        for (int i = 0; i < listOfIntegers.size(); i++) {
            int newTarget = (int) target - listOfIntegers.get(i);
            if (checker.containsKey(newTarget)) {
                res.add(checker.get(newTarget));
                res.add(i);
                return res;
            }
            checker.put(listOfIntegers.get(i), i);
        }
        return null;
    }

    private static <T> List<Integer> checkOfTarget(int[] ints, T target, Map<Integer, Integer> checker, List<Integer> res) {
        for (int i = 0; i < ints.length; i++) {
            int newTarget = (int) target - ints[i];
            if (checker.containsKey(newTarget)) {
                res.add(checker.get(newTarget));
                res.add(i);
                return res;
            }
            checker.put(ints[i], i);
        }
        return null;
    }

}
