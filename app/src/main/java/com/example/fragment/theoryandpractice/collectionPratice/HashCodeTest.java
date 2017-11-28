package com.example.fragment.theoryandpractice.collectionPratice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by xiaolong.
 * on 2016-01-28 下午3:25.
 *
 * hashCode是jdk根据对象的地址或者字符串或者数字算出来的int类型的数值
 * 指向同一地址的对象 hashCode是一样
 * 内容相同的字段串 hashCode是一样
 */
public class HashCodeTest {

    public static void main(String[] arge){

        MyKey k1=new MyKey();
        k1.keyName=new String("1");

        MyKey k2=new MyKey();
        k2.keyName=new String("1");

        System.out.println("K1:"+k1.hashCode());
        System.out.println("K2:"+k2.hashCode());
        System.out.println(k2.equals(k1)+"  "+(k2==k1));

        k2=k1;

        System.out.println("K1:"+k1.hashCode());
        System.out.println("K2:" + k2.hashCode());
        System.out.println(k2.equals(k1) + "  " + (k2 == k1));


        List<String> list=new ArrayList<>();
        list.add("1");
        list.add("2");

        List<String> list2=new ArrayList<>();
        list2.addAll(list);

        System.out.println(list2.size());
        list.clear();
        System.out.println(list2.size());

    }
}
