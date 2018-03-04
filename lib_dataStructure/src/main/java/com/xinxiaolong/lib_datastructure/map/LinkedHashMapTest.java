package com.xinxiaolong.lib_datastructure.map;

import java.util.LinkedHashMap;

/**
 * Created by xiaolong on 2017/12/22.
 * email：xinxiaolong123@foxmail.com
 */

public class LinkedHashMapTest {


    public static void main(String[] arg){

        LinkedHashMap<String,String> linkedHashMap=new LinkedHashMap<String, String>(16,0.75f,true);

        linkedHashMap.put("1","1");
        linkedHashMap.put("2","2");
        linkedHashMap.put("3","3");
        linkedHashMap.put("4","4");
        linkedHashMap.put("5","5");
        linkedHashMap.put("6","6");

    }
}
