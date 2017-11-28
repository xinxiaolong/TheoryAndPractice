package com.example.fragment.theoryandpractice.threadPractice.sync;

import java.util.concurrent.Semaphore;

/**
 * Created by xiaolong.
 * on 2016-01-16 下午2:55.
 *
 * Semaphore信号量：
 *
 * 可以维护当前访问自身的线程个数，并提供了同步机制。使用Semaphore可以控制同时访问资源的线程个数，例如实现一个文件允许的并发访问数。
 * 那它和线程池有何区别
 *
 * 1：线程池是线程复用的；信号量是线程同步的
 * 2：线程池是多个线程异步执行任务，信号量是控制任务中的多线程同步区域。
 */
public class SemaphoreTest {

    public static void main(String[] agre) {
        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < 20; i++) {
            new Thread(new MySRunnable(semaphore)).start();
        }
    }
}

class MySRunnable implements Runnable {
    Semaphore semaphore;
    MySRunnable(Semaphore semaphore) {
        super();
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();//获取执行权限
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行了线程" + Thread.currentThread().getName());
        semaphore.release();//释放执行权限
    }
}

