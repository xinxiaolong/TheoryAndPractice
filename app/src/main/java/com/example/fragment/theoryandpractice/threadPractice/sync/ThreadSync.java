package com.example.fragment.theoryandpractice.threadPractice.sync;

/**
 * Created by xiaolong.
 * on 2016-01-13 下午4:01.
 */
public class ThreadSync {

    public static void main(String[] arge){

        MyRunnable runnable=new MyRunnable();
        new Thread(runnable,"1").start();
        new Thread(runnable,"2").start();
        new Thread(runnable,"3").start();
        new Thread(runnable,"4").start();
        new Thread(runnable,"5").start();
    }
}

class MyRunnable implements Runnable{
    @Override
    public void run() {
        //syncPrintInfo();
        printInfo();
    }

    private synchronized void syncPrintInfo(){
        printInfo();
    }

    private void printInfo(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }
}
