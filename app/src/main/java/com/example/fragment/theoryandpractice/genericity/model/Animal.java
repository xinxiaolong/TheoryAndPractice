package com.example.fragment.theoryandpractice.genericity.model;

/**
 * Created by xiaolong on 2017/11/11.
 * email：xinxiaolong123@foxmail.com
 */
public class Animal implements AnimalBehavior{
    public void shout() {
        System.out.println("我是个动物");
    }

    @Override
    public void run() {

    }
}
