package com.example.fragment.theoryandpractice.threadPractice.producersAndConsumers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xiaolong.
 * on 2016-01-16 下午3:45.
 */
public class ProducersAndConsumers_Lock {

    public static void main(String[] agre) throws InterruptedException {
        BreadFactory_Lock breadFactory=new BreadFactory_Lock();
        new Thread(new Producers_Lock(breadFactory)).start();
        new Thread(new Consumers_Lock(breadFactory),"消费者A").start();
        new Thread(new Consumers_Lock(breadFactory),"消费者B").start();

        while (true){
            Thread.sleep(1000);
            System.out.println("1秒");
        }
    }
}

class Producers_Lock implements Runnable{

    BreadFactory_Lock breadFactory;
    public Producers_Lock(BreadFactory_Lock breadFactory){
        this.breadFactory=breadFactory;
    }
    @Override
    public void run() {

        while (true){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Bread bread=new Bread();
            breadFactory.toProducerBread(bread);
        }
    }
}


class Consumers_Lock implements Runnable{

    BreadFactory_Lock breadFactory;
    public Consumers_Lock(BreadFactory_Lock breadFactory){
        this.breadFactory=breadFactory;
    }
    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            breadFactory.toConsumerBread();
        }

    }
}


class BreadFactory_Lock{
    List<Bread> breads=new ArrayList<>();

    Lock lock=new ReentrantLock();
    Condition put_condition=lock.newCondition();
    Condition get_condition=lock.newCondition();

    public void  toConsumerBread(){
        lock.lock();
        if(breads.size()==0){
            try {
                System.out.println(Thread.currentThread().getName()+"准备等待...");
                get_condition.await();
                System.out.println(Thread.currentThread().getName() + "被唤醒了，准备继续消费");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(breads.size()==0){
            return;
        }
        System.out.println(Thread.currentThread().getName() + "消费了第" + breads.size() + "面包");
        breads.remove(breads.size() - 1);

        if(breads.size()==0){
            put_condition.signal();
        }

        lock.unlock();
    }

    public void toProducerBread(Bread bread) {

        lock.lock();

        if(breads.size()==10){
            try {
                put_condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        breads.add(bread);
        System.out.println("生产到第" + breads.size() + "面包");
        if(breads.size()==1){
            get_condition.signalAll();
        }
        lock.unlock();
    }
}
