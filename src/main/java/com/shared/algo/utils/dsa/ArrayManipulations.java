package com.shared.algo.utils.dsa;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Scope("singleton")
public class ArrayManipulations {

    public List<Integer> sumOfTwoIsTarget(int[] arr, int target) {
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

    public List<List<Integer>> sumOfThreeIsTarget(int[] arr, int target) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (i == 0 || arr[i] != arr[i - 1]) {
                int j = i + 1;
                int k = arr.length - 1;
                int newTarget = target - arr[i];
                while (j < k) {
                    if (arr[j] + arr[k] == newTarget) {
                        List<Integer> resSet = new ArrayList<>();
                        resSet.add(arr[i]);
                        resSet.add(arr[j]);
                        resSet.add(arr[k]);
                        res.add(resSet);
                        while (j < k && arr[j] == arr[j + 1]) j++;
                        while (j < k && arr[k] == arr[k - 1]) k--;
                        j++;
                        k--;
                    } else if (arr[j] + arr[k] < newTarget) {
                        j++;
                    } else {
                        k--;
                    }
                }
            }
        }
        return res;
    }

    public Integer smallestNumberForActualProduct(int givenNumber) {
        String answer = "";
        for (int i = 9; i > 1; i--) {
            while (givenNumber % i == 0) {
                givenNumber = givenNumber / i;
                answer = i + answer;
            }
        }

        if (givenNumber != 1) {
            return -1;
        } else {
            return Integer.parseInt(answer);
        }
    }

    public int[] separateEvenAndOdd(int[] a) {
        int i = 0;
        int j = a.length - 1;

        while (i < j) {
            while (i < j && a[j] % 2 != 0)
                j--;
            while (i < j && a[i] % 2 == 0)
                i++;

            if (i < j) {
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                i++;
                j--;
            }
        }

        return a;
    }

    public List<Integer> extractArrayElementsSpecifically(int[] a) {
        List<Integer> response = new ArrayList<>();

        for (int number : a) {
            if (String.valueOf(number).startsWith("2"))
                response.add(number);
        }
        return response;
    }

    public Map<Integer, Integer> checkOccurrences(int[] a){
        Map<Integer, Integer> response = new LinkedHashMap<>();
        for (int number : a) {
            if(response.containsKey(number)){
                response.put(number, response.get(number) + 1);
            }else{
                response.put(number, 1);
            }
        }

        return response;
    }

    public void printSperical(int[][] a) {
        int rowMin = 0, rowMax = a[0].length - 1, colMin = 0, colMax = a.length - 1, n = a.length, m = a[0].length, count = 0;

        while (count < (n * m)) {
            //top boundary
            for (int row = colMin; row <= colMax && count < (n * m); row++) {
                System.out.print(a[rowMin][row] + " ");
                count++;
            }
            rowMin++;
            //right boundary
            for (int col = rowMin; col <= rowMax && count < (n * m); col++) {
                System.out.print(a[col][colMax] + " ");
                count++;
            }
            colMax--;
            //bottom boundary
            for (int row = colMax; row >= colMin && count < (n * m); row--) {
                System.out.print(a[rowMax][row] + " ");
                count++;
            }
            rowMax--;
            //left boundary
            for (int col = rowMax; col >= rowMin && count < (n * m); col--) {
                System.out.print(a[col][colMin] + " ");
                count++;
            }
            colMin++;
        }
    }
}
