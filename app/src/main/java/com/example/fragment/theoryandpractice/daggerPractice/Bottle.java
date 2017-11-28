package com.example.fragment.theoryandpractice.daggerPractice;

import android.util.Log;

/**
 * Created by xiaolong on 2017/1/10.
 */

public class Bottle {

    Drink drink;
    public void setDrink(Drink drink){
        this.drink=drink;
    }

    public void print(){
        Log.e(Bottle.class.getName(),"这个是装"+drink.getContent()+"的瓶子");
    }
}
