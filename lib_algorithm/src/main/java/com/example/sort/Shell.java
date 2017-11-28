package com.example.sort;

/**
 * Created by xiaolong on 2017/8/24.
 * email：xinxiaolong123@foxmail.com
 * 希尔排序
 */

class Shell extends Base{

    // sort the array a[] in ascending order using Shellsort
    public static void sort(Comparable[] a) {
        int N = a.length;
        // 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ...
        int h = 1;
        while (h < N/3){
            h = 3*h + 1;
        }

        while (h >= 1) {
            // h-sort the array
            for (int i = h; i < N; i++) {
                int j=i;
                for (; j >= h && less(a[j], a[j-h]); j -= h) {
                    exch(a, j, j-h);
                }
            }
            h /= 3;
        }
    }


    // sort the array a[] in ascending order using Shellsort
    public static void sort2(Comparable[] a) {
        int N = a.length;

        show(a);

        // 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ...
        int h = 1;
        while (h < N/3){
            h = 3*h + 1;
        }

        while (h >= 1) {
            // h-sort the array
            for (int i = h; i < N; i++) {

                int j=i;

                for (; j >= h; j -= h) {
                    System.out.println("h="+h+"   j="+j+" j-h="+(j-h)+" a[j]="+a[j]+"  a[j-h]"+a[j-h]);
                    if(less(a[j], a[j-h])){
                        exch(a, j, j-h);
                    }
                    show(a);
                }
                System.out.println("一波排序完成");
            }
            System.out.println("步长为"+h+"排序完成");
            show(a);
            System.out.println("================================");
            assert isHsorted(a, h);
            h /= 3;
        }
        assert isSorted(a);
    }

    public static void sort4(Comparable[] a){

        int len=a.length;
        int gap=0;
        while (gap < len / 3)
            gap = gap * 3 + 1; // <O(n^(3/2)) by Knuth,1973>: 1, 4, 13, 40, 121, ...

        while (gap>0){
            for (int i=0;i<gap;i++){
                 for (int n=i;n<len;n=n+gap){
                     for (int m=n;m>=gap&&less(a[m], a[m-gap]);m-=gap){
                            exch(a,m,m-gap);
                     }
                 }
            }
            gap/=3;
        }
    }
}
