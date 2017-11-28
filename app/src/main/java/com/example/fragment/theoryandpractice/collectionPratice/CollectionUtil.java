package com.example.fragment.theoryandpractice.collectionPratice;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by xiaolong on 2017/9/26.
 * email：xinxiaolong123@foxmail.com
 */

public class CollectionUtil {

    public static void print(Object[] objects){
        if(objects==null||objects.length==0){
            System.out.println("数组为空!");
            return;
        }
        for (int i=0;i<objects.length;i++){
            System.out.print("["+i+"]="+objects[i]+"    ");
        }
        System.out.println();
    }


    public static void print(Collection list){
        if(list==null||list.size()==0){
            System.out.println("数组为空!");
            return;
        }
        Iterator iterator=list.iterator();
        int i=0;
        while (iterator.hasNext()){
            System.out.print("["+i+"]="+iterator.next()+"    ");
            i++;
        }
        System.out.println();
    }
}
