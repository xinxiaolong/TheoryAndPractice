package com.example.sort;

import com.example.stdLib.StdOut;

/**
 * Created by xiaolong on 2017/8/8.
 * email：xinxiaolong123@foxmail.com
 */

public class Main {

    public static void main(String[] args){
        Selection.sort(Database.getIntList());
        Insertion.sort(Database.getIntList());
        //Merge.sort(Database.getStringList());
        //sortCompare();

        sort(0,15);
    }

    private static void sortCompare(){
        int N=1000000;
        int T=1;
        String algorithm1="Merge";
        String algorithm2="Shell";
        double t1 = SortCompare.timeRandomInput(algorithm1,N,T);
        System.out.println(algorithm1+": 使用时长"+t1);

        double t2 =SortCompare.timeRandomInput(algorithm2,N,T);
        System.out.println(algorithm2+": 使用时长"+t2);

        System.out.println("用时比例"+t2/t1);
    }


    public static int fact(int n){
        if(n == 0)
            return 1;
        else
            return (n*fact(n-1));
    }

    // mergesort a[lo..hi] using auxiliary array aux[lo..hi]
    private static void sort(int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;

        sort(lo, mid);
        sort(mid+1, hi);

        System.out.println("=============in sort lo="+lo+"   mid="+mid+"   hi="+hi);

    }


}
