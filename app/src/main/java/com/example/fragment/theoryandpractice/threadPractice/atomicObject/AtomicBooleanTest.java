package com.example.fragment.theoryandpractice.threadPractice.atomicObject;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by xiaolong.
 * on 2016-01-20 下午12:07.
 */
public class AtomicBooleanTest {

    public static void main(String[] arge){
        AtomicRunnable atomicRunnable=new AtomicRunnable();
        for (int i=0;i<100;i++){
            Thread thread =new Thread(atomicRunnable);
            thread.start();
        }
    }
}

class AtomicRunnable implements Runnable{

    AtomicBoolean atomicBoolean=new AtomicBoolean(false);
    boolean aBoolean=false;
    @Override
    public void run() {
        if(atomicBoolean.compareAndSet(false,true)){
            System.out.println(Thread.currentThread().getName()+"进入睡眠");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"睡醒了,去干活");
        }else{
            System.out.println(Thread.currentThread().getName()+"没有睡觉，直接干活去了");
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"活干完了");
    }
}

