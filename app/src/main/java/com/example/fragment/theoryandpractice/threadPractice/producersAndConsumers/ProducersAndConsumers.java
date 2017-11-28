package com.example.fragment.theoryandpractice.threadPractice.producersAndConsumers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaolong.
 * on 2016-01-16 下午12:43.
 *
 *
 * 消费者和生产者例子主要是对线程间的通讯有所了解的一个知识点
 * 这里是用的wait和notyfyAll来做通讯的
 * wait必须放于被synchronized的代码片段里，wait方法会使当前执行这个代码区的线程处理等待状态，注意这个时候它已经放开了锁
 * notyfyAll也是必须放于被synchronized的代码片段里，它会激活当前所有访问这个代码片段上处于wait的线程，叫他们从新进行排队执行。
 *
 *
 * wait和sleep的主要区别
 * sleep()睡眠时，保持对象锁，仍然占有该锁；
 * wait()睡眠时，释放对象锁。
 */
public class ProducersAndConsumers {



   public static void main(String[] agre) throws InterruptedException {
       BreadFactory breadFactory=new BreadFactory();
       new Thread(new Producers(breadFactory)).start();
       new Thread(new Consumers(breadFactory),"消费者A").start();
       new Thread(new Consumers(breadFactory),"消费者B").start();
       new Thread(new Consumers(breadFactory),"消费者C").start();
       new Thread(new Consumers(breadFactory),"消费者D").start();
       new Thread(new Consumers(breadFactory),"消费者E").start();

       while (true){
           Thread.sleep(1000);
           System.out.println("1秒");
       }
   }
}

class Bread {
    int breadIndex;
}

class Producers implements Runnable{

    BreadFactory breadFactory;
    public Producers(BreadFactory breadFactory){
        this.breadFactory=breadFactory;
    }
    @Override
    public void run() {

        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Bread bread=new Bread();
            breadFactory.toProducerBread(bread);
        }
    }
}


class Consumers implements Runnable{

    BreadFactory breadFactory;
    public Consumers(BreadFactory breadFactory){
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
            breadFactory.toConsumerBread();
        }

    }
}


class BreadFactory{

    List<Bread> breads=new ArrayList<>();

    public synchronized void  toConsumerBread(){
        if(breads.size()==0){
            try {
                System.out.println(Thread.currentThread().getName()+"准备等待...");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "被唤醒了，准备继续消费");
        if(breads.size()==0){
            return;
        }
        System.out.println(Thread.currentThread().getName() + "消费了第" + breads.size() + "面包");
        breads.remove(breads.size() - 1);

        if(breads.size()==0){
            notifyAll();
        }
    }

    public synchronized void toProducerBread(Bread bread) {
        if(breads.size()==10){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        breads.add(bread);
        System.out.println("生产到第" + breads.size() + "面包");
        if(breads.size()==1){
            notifyAll();
        }
    }
}