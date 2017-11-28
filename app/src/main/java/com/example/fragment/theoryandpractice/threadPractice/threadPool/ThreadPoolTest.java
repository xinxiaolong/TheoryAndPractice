package com.example.fragment.theoryandpractice.threadPractice.threadPool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by xiaolong.
 * on 2016-01-19 下午3:05.
 */
public class ThreadPoolTest {


    public static void main(String[] agre){
        SimpleThreadPoolFactory factory=new SimpleThreadPoolFactory();
        ExecutorService executor=factory.createFixedThreadPool(2);
        for(int i=0;i<5;i++){
            PoolRunnble runnble=new PoolRunnble();
            executor.execute(runnble);
        }
        //这个方法是禁止向线程池中加入任何,和停止正在进行的特殊状态下的线程,处于sleep,await等状态
        executor.shutdownNow();


        executor=factory.createSingleThreadExecutor();

        executor.execute(new CountRunnble("第一个"));
        executor.execute(new CountRunnble("第二个"));
        executor.execute(new CountRunnble("第三个"));
        executor.execute(new CountRunnble("第四个"));


        executor=factory.createCachedThreadPool();
        for(int i=0;i<10;i++){
            PoolRunnble runnble=new PoolRunnble();
            executor.execute(runnble);
        }

        ScheduledThreadPoolExecutor executor2=factory.createScheduledThreadPoolExecutor(1);
        executor2.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("===========美隔一秒钟执行一次");
            }
        },1000, 5000, TimeUnit.MILLISECONDS);

        executor2.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("===========美隔再一秒钟执行一次");
            }
        },1000, 5000, TimeUnit.MILLISECONDS);

    }
}
