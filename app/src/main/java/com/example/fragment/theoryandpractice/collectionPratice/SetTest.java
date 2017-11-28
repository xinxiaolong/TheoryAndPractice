package com.example.fragment.theoryandpractice.collectionPratice;

import com.android.internal.util.Predicate;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by xiaolong on 2016/12/7.
 */

public class SetTest {

    public static void main(String[] args){

        //创建一个空的set对象
        Set<String> stringSet=Collections.emptySet();
        System.out.print(stringSet);
        stringSet.add("第一条数据");

        //产生一个不可修改的Set对象
        stringSet=Collections.unmodifiableSet(stringSet);
        System.out.print(stringSet);
        stringSet.add("第二条数据");

        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
        arrayList.add("5");
        arrayList.add("6");
        arrayList.add("1");
        arrayList.add("2");

        Collection c;
        Map map;
        AbstractMap absMap;
    }

}
