package com.shared.algo.utils.dsa;

import org.springframework.stereotype.Component;

@Component
public class BinarySearch {

    public int searchForTargetFirstOcc(int[] a, int t) {
        int left = 0, right = a.length - 1, first = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (a[mid] == t) {
                first = mid;
                right = right - 1;
            } else if (a[mid] < t) {
                left = left + 1;
            } else {
                right = right - 1;
            }
        }
        return first;
    }

    public int searchForTargetLastOcc(int[] a, int t) {
        int left = 0, right = a.length - 1, last = 0;
        while (left < right) {
            int mid = (left + right) / 2;
            if (a[mid] == t) {
                last = mid;
                left = left + 1;
            } else if (a[mid] < t) {
                left = left + 1;
            } else {
                right = right - 1;
            }
        }

        return last;
    }

    public void searchForTargetFirstAndLastOcc(int[] a, int t) {
        int left = 0, right = a.length - 1, first = 0;
        while (left < right) {
            int mid = (left + right) / 2;
            if (a[mid] == t) {
                first = mid;
                right = right - 1;
            } else if (a[mid] < t) {
                left = left + 1;
            } else {
                right = right - 1;
            }
        }

        left = 0;
        right = a.length - 1;
        int last = 0;
        while (left < right) {
            int mid = (left + right) / 2;
            if (a[mid] == t) {
                last = mid;
                left = left + 1;
            } else if (a[mid] < t) {
                left = left + 1;
            } else {
                right = right - 1;
            }
        }
    }
}
