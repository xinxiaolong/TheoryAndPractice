package com.example.sort;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.List;

/**
 * Created by xiaolong on 2017/8/24.
 * email：xinxiaolong123@foxmail.com
 * 归并排序
 */

public class Merge extends Base{

    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length-1);
        System.out.println(isSorted(a));
    }

    // mergesort a[lo..hi] using auxiliary array aux[lo..hi]
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {

        System.out.println("+++++++++++++in sort lo="+lo+"  hi="+hi);

        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;

        System.out.println("=============in sort lo="+lo+"   mid="+mid+"   hi="+hi);
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    // stably merge a[lo .. mid] with a[mid+1 .. hi] using aux[lo .. hi]
    public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {

        System.out.println("in merge   lo="+lo+"   mid="+mid+"   hi="+hi);
        // precondition: a[lo .. mid] and a[mid+1 .. hi] are sorted subarrays
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid+1, hi);

        System.arraycopy(a,0,aux,0,a.length);

        int leftIndex=lo,rightIndex=mid+1;
        for (int i=lo;i<=hi;i++){
            if(leftIndex>mid){            //如果左边取完了，从右边按顺序取
                a[i]=aux[rightIndex++];
            }else if(rightIndex>hi){      //如果右边取完了，从左边按顺序取
                a[i]=aux[leftIndex++];
            }else if(less(aux[leftIndex],aux[rightIndex])){
                a[i]=aux[leftIndex++];
            }else {
                a[i]=aux[rightIndex++];
            }
        }
        // postcondition: a[lo .. hi] is sorted
        assert isSorted(a, lo, hi);
    }
}
