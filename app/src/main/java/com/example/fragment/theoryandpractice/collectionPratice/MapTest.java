package com.example.fragment.theoryandpractice.collectionPratice;

import android.support.v4.util.SparseArrayCompat;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * Created by xiaolong.
 * on 2016-01-29 下午7:20.
 *
 * Hashmap 是一个最常用的Map,它根据键的HashCode值存储数据,根据键可以直接获取它的值，具有很快的访问速度，遍历时，取得数据的顺序是完全随机的。
 * HashMap最多只允许一条记录的键为Null;允许多条记录的值为 Null;HashMap不支持线程的同步，即任一时刻可以有多个线程同时写HashMap;可能会导致数据的不一致。
 * 如果需要同步，可以用Collections的synchronizedMap方法使HashMap具有同步的能力，或者使用ConcurrentHashMap。
 *
 */
public class MapTest extends HashMap{

    public static void main(String[] agre){
        HashMap map=new HashMap();

        map.put("1","1");
        map.put("2","2");
        map.put("3","3");
        map.put("4","4");

        Iterator iterator=map.entrySet().iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("------------------");

        iterator=map.entrySet().iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("------------------");

        iterator=map.entrySet().iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        SparseArrayCompat<String> modes = new SparseArrayCompat<>();
        modes.put(1,"1");
        modes.remove(2);
        modes.size();

        for (int i=0;i<10;i++){
            System.out.println(modes.get(i));
        }

        int num=40;
        String name=null;
        System.out.println("====================================="+(num/=4));
        System.out.println("====================================="+num);

        assert name!=null;

        System.out.println(gcd(60, 42));
        derate(60,42);
    }

    //求最大公约数
    private static int gcd(int width, int height) {
        while (height != 0) {
            int c = height;
            height = width % height;
            width = c;
        }
        return width;
    }

    private static int gcd2(int width, int height){
        int max=width>height?width:height;
        int min=width>height?height:width;
        if((max%2==0)&&(min%2==0)){
            max=max%2;
            min=min%2;
        }
        int diff=max-min;
        if(diff==min){
            return diff;
        }
        return 0;
    }

    //求最大公约数
    private static void derate(int num1,int num2){
        int max=num1>num2?num1:num2;
        int min=num1>num2?num2:num1;
//        if((max%2==0)&&(min%2==0)){
//            max=max/2;
//            min=min/2;
//        }
        int diff=max-min;
        if(diff==min){
            System.out.print("最大公约数"+diff);
            return ;
        }else {
            derate(diff,min);
        }
    }
}
