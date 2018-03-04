package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaolong on 2017/12/19.
 * email：xinxiaolong123@foxmail.com
 */

public class GCTest {

    public static void main(String[] arg){
        List<Apple> appleList=new ArrayList();

        for (int i=0;i<10;i++){

            Apple apple=new Apple();

            apple.name="苹果"+i;

            appleList.add(apple);

            apple=null;
        }


        for (int i=0;i<appleList.size();i++){
            Apple apple=appleList.get(i);
            if(apple!=null){
                System.out.println(apple.name);
            }else {
                System.out.println("null");
            }
        }
    }


    static class Apple{
        public String name;
    }
}
