package com.example.fragment.theoryandpractice.threadPractice.sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xiaolong.
 * on 2016-01-16 下午3:13.
 * ReentrantLock
 * 类实现了Lock,它拥有与synchronized相同的并发性和内存语义，但是添加了类似锁投票、定时锁等候和可中断锁等候的一些特性。
 */
public class ReentrantLockTest {

    public static void main(String[] agre) {
        Lock lock=new ReentrantLock();
        for (int i = 0; i < 10; i++) {
            new Thread(new MyRRunnable(lock)).start();
        }
    }
}

class MyRRunnable implements Runnable {
    Lock lock;
    MyRRunnable(Lock lock) {
        super();
        this.lock = lock;
    }
    @Override
    public void run() {
        lock.lock();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行了线程" + Thread.currentThread().getName());
        lock.unlock();
    }


}
