package com.example.sort;

/**
 * Created by xiaolong on 2017/8/8.
 * email：xinxiaolong123@foxmail.com
 * 插入排序法
 */

public class Insertion extends Base {

    public static void sort(Comparable[] a) {
        int length = a.length;

        for (int i = 1; i < length; i++) {
            Comparable temp = a[i];
            int j = i - 1;
            for (; j >= 0 && less(temp, a[j]); j--) {
                a[j + 1] = a[j];
            }
            a[j + 1] = temp;
        }
    }

    public static void sort1(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
                exch(a, j, j-1);
            }
        }
    }

}
