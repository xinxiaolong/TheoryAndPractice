package com.example.fragment.theoryandpractice.collectionPratice;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by xiaolong.
 * on 2016-01-19 下午5:31.
 *
 * 此种Map的特点是，当除了自身有对key的引用外，此key没有其他引用那么此map会自动丢弃此值，
 * 注意这里key是弱引用
 */
public class WeakHashMapTest {

    public static void main(String[] agre){

        /**
         * 下面例子是 在weakmap中对应的key为null的时候
         *  并且没有地方引用这个key对象的时候
         *  系统会将自动remove这条数据，也就是帮你回收这条内存
         */

        WeakHashMapKey weakHashMapKey1=new WeakHashMapKey("1");
        WeakHashMapKey weakHashMapKey2=new WeakHashMapKey("1");
        WeakHashMapKey weakHashMapKey3=new WeakHashMapKey("1");

        Map<WeakHashMapKey,String> weakmap = new WeakHashMap();

        weakmap.put(weakHashMapKey1, "111");
        weakmap.put(weakHashMapKey2, "222");
        weakmap.put(weakHashMapKey3, "333");

        weakHashMapKey1=null;

        printMapValue(weakmap);


        System.gc();
        System.out.println("等待gc回收内存");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        printMapValue(weakmap);

        double num=239.34*100;
        System.out.println(num);

    }


    private static void printMapValue(Map<WeakHashMapKey,String> map){
        Iterator j = map.entrySet().iterator();
        while (j.hasNext()) {
            Map.Entry en = (Map.Entry)j.next();
            System.out.println("weakmap:"+en.getKey().toString()+":"+en.getValue());

        }
    }


}

class WeakHashMapKey {

    public String name;
    public WeakHashMapKey(String name){
        this.name=name;
    }

    @Override
    public String toString() {
        return name;
    }
}
