package com.example.fragment.theoryandpractice.threadPractice.threadPool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xiaolong.
 * on 2016-01-13 下午1:41.
 */
public class SimpleThreadPoolFactory {

    final int MAX_THREAD_COUNT = 2;

    ExecutorService executor;

    /**
     * 使用的Thread对象的数量是有限的,如果提交的任务数量大于限制的最大线程数，
     * 那么这些任务讲排队，然后当有一个线程的任务结束之后，将会根据调度策略继续等待执行下一个任务
     * 线程执行完毕不再被复用时就被回收
     */
    public ExecutorService createFixedThreadPool(int nThreads) {
        executor = Executors.newFixedThreadPool(nThreads);
        return executor;
    }

    /**
     * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程
     * 线程执行完毕，可以允许空闲1分钟
     */
    public ExecutorService createCachedThreadPool() {
        executor = Executors.newCachedThreadPool();
        return executor;
    }


    /**
     * 返回一个线程池（这个线程池只有一个线程）
     * ,这个线程池可以在线程死后（或发生异常时）重新启动一个线程来替代原来的线程继续执行下去！
     */
    public ExecutorService createSingleThreadExecutor() {
        executor = Executors.newSingleThreadExecutor();
        return executor;
    }


    /**
     * 使用的Thread对象的数量是有限的,如果提交的任务数量大于限制的最大线程数，
     * 那么这些任务讲排队，然后当有一个线程的任务结束之后，将会根据调度策略继续等待执行下一个任务
     * 线程执行完毕不再被复用时就被回收
     */
    public ScheduledThreadPoolExecutor createScheduledThreadPoolExecutor(int nThreads) {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(nThreads);
        return executor;
    }
}


class  CountRunnble implements Runnable {

    String name;
    public CountRunnble(String name){
        this.name=name;
    }
    @Override
    public void run() {
        int i=0;
       while (true){
           i++;
           System.out.println(Thread.currentThread().getName()+ name+ "计数 i=" + i);
           try {
               Thread.sleep(400);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           if(name.equals("第三个")||name.equals("第2个")){
               //当走到这里的时候会遇到异常,使用线程池的时候且线程池只有一个线程,会再起一个新的线程执行后面加入的任务。
               String s=null;
               s.equals("");
           }
           if(i==7){
             return;
           }
       }
    }
}

class PoolRunnble implements Runnable {

    @Override
    public void run() {
        double d = Math.random();
        int sleepSecond = (int) (d * 5000);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return;
        }
        System.out.println(Thread.currentThread().getName() + "执行完毕，睡眠了" + sleepSecond / 1000);
    }
}
