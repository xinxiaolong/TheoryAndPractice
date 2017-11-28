package com.example.fragment.theoryandpractice.daggerPractice;

import android.util.Log;

import dagger.Module;

/**
 * Created by xiaolong on 2017/1/10.
 */

public class AppleDrink implements Drink{

    Apple apple;
    public AppleDrink(Apple apple){
        this.apple=apple;
    }

    public String getContent(){
        return "这是一杯"+apple.getName()+"饮料";
    }

    public void print(){
        System.out.print("这是一杯"+apple.getName()+"饮料");
        Log.e(AppleDrink.class.getName(),getContent());
    }
}
