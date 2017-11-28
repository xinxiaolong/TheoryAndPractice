package com.example.fragment.theoryandpractice.collectionPratice;

import com.example.fragment.theoryandpractice.utils.ApUtils;

/**
 * Created by xiaolong on 2016/12/14.
 */

public class CloneTest {

    public static void main(String[] agre){
        Apple apple1=new Apple();
        apple1.name="123";
        apple1.print();
        Apple apple2=apple1.clone();
        apple2.print();

        ApUtils.println(apple1==apple2);
    }
}


class Apple implements Cloneable{
    public String name="默认名字";
    public void print(){
        System.out.println("我是苹果");
    }
    public Apple clone(){
        Apple apple= null;
        try {
            apple = (Apple) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            ApUtils.println(e.getMessage());
        }
        return apple;
    }
}
