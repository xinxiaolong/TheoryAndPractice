package com.example.sort;

/**
 * Created by xiaolong on 2017/8/29.
 * email：xinxiaolong123@foxmail.com
 */

public class ShellX extends Base{

    public static void sort(Comparable[] arr) {
        int gap = 1, i, j, len = arr.length;
        Comparable temp;
        while (gap < len / 3)
            gap = gap * 3 + 1; // <O(n^(3/2)) by Knuth,1973>: 1, 4, 13, 40, 121, ...
        for (; gap > 0; gap /= 3)
            for (i = gap; i < len; i++) {
                temp = arr[i];
                for (j = i - gap; j >= 0 && less(temp,arr[j]); j -= gap)
                    arr[j + gap] = arr[j];

                arr[j + gap] = temp;
            }
    }

}
