package com.example.sort;

/**
 * Created by xiaolong on 2017/8/8.
 * email：xinxiaolong123@foxmail.com
 * 选择排序法
 */

public class Selection extends Base {

    public static void sort(Comparable[] a) {
        int length = a.length;
        for (int i=0;i<length;i++){
            int minIndex=i;
            for (int j=i+1;j<length;j++){
                if(less(a[j], a[minIndex])){
                    minIndex = j;

                }
            }
            exch(a, i, minIndex);
        }
    }

    public static void sort1(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i+1; j < N; j++) {
                if (less(a[j], a[min])) min = j;
            }
            exch(a, i, min);
        }
    }

}
