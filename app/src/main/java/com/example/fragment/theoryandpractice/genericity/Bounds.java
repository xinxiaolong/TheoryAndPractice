package com.example.fragment.theoryandpractice.genericity;


import android.view.View;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by xiaolong on 2016/12/6.
 */

public class Bounds {
    public static void main(String[] args) {


        Food fruit = new Apple();
        fruit.print();


        //**************************************为什么需要上下界通配符***********************************//

        // 这里有一个放东西的盘子Plate对象,接受一个东西类型的泛型T。
        // 想写一个能放任何水果的盘子,因为Apple是继承Fruit的
        // Plate<Fruit> p=new Plate<Apple>(new Apple());
        // 但是编译失败,因为这样的泛型是没有指明任何的继承关系的,只有盘子里的物品有继承关系。

        Plate<Goods> p1 = new Plate<Goods>(new Apple());//放一个苹果进去
        p1.set(new Gala());//放一个嘎啦苹果进去。
        p1.set(new Meat());//放一个肉进去。
        p1.set(new Fruit());//放一个水果进去。
        p1.get().print();


        //如何才设计一个更加宽泛的盘子呢,比如说放属于食物的但可以指定类型的东西到盘子去
        //Sun的大脑袋们就想出了<? extends T>和<? super T>的办法


        //**************************************上界通配符***********************************//

        //先说"上界通配符" Plate<？ extends Food> 一个能放食物以及一切是食物派生类的盘子。
        //"上界通配符" 不能往里存，只能往外取。为什么?下面有分析
        Plate<? extends Food> plateFood = new Plate<Fruit>(new Apple());

        Food g0 = plateFood.get(); //这里运行会报错,逃过了编辑器的掌心,但我怀疑是编辑器的bug。

        plateFood = new Plate(new Meat());//成为肉盘子
        plateFood = new Plate(new Fruit());//成为水果盘
        plateFood = new Plate(new Apple());//成为苹果盘子
        //plate1.set(new Fruit()); set方法失效
        plateFood.get().print();
        Food g = plateFood.get();//这里可以直接取到为Food的对象。

        //为什么只有get()有用,而set()方法失效了也就是说再初始化的时候只能set一次
        //上界规定了泛型的最大颗粒。拿这个例子说就是盘子可放东西最终被转换成了Food或Food的子类对象,虽然传入Goods编译没错,单运行会崩溃,我认为是编辑器的问题。
        //为什么有get因为所有类型都可以转为Food
        //为什么没set因为根本就无法控制放进来类型和上次的要一样。比如第一次是肉,第二次是苹果。


        //**************************************下界通配符***********************************//

        //再说说 "下界通配符" Plate<？ super Fruit> 一个能放水果以及一切是水果基类的盘子。
        //可以往里存，但往外取只能放在Object对象里。
        Plate<? super Food> plate2 = new Plate(new Goods());
        Goods food=(Goods)plate2.get();
        food.print();
        plate2.set(new Fruit());
        plate2.set(new Apple());
        plate2.set(new Gala());
        //为什么这里可以set但是get只能是Object了呢
        //因为下界规定了元素的最小粒度,拿这个例子说就是盘子可放最小为Fruit及其子类类型,最大至Object
        //为什么有set?因为这里控制了最小的颗粒,所以只要set方法可做控制:进来Fruit及其子类类型就可以了
        //为什么get只能是Object?因为这里初始化时接受的比Fruit大的类型,所以这里不能转为Friut不然会丢失数据。

        //****************这个方法很特殊******************//
        Goods food1=getInstance(new Gala());
        food1.print();

        ArrayList<? extends Fruit> arrayList1 = new ArrayList<>();
        // arrayList1.add(new Goods()); 不可以add
        //arrayList1.get(0).print();


        ArrayList<? super Food> arrayList2 = new ArrayList<>();
        arrayList2.add(new Fruit());
        arrayList2.add(new Apple());
        arrayList2.add(new Meat());

        print(arrayList1);

        ArrayList<Apple> goodsArrayList=new ArrayList<>();
        print(goodsArrayList);


        ArrayList<Goods> foodArrayList=new ArrayList();
        print1(foodArrayList);

    }


    public static void print(ArrayList<? extends Food> arrayList) {
        arrayList.get(0).print();
    }


    public static void print1(ArrayList<? super Fruit> arrayList) {
        arrayList.add(new Apple());
        arrayList.add(new Gala());
    }

    public  static <T extends Goods> T getInstance(Goods o){
        return (T)o;
    }

    public static Goods getGoods(){
        return new Goods();
    }

    public static <T extends View> T findById(View view, int id) {
        return (T)view.findViewById(id);
    }

}

class Plate<T> {
    private T item;

    public Plate(T item) {
        set(item);
    }

    public void set(T t) {
        item = t;
    }

    public void sets(T t) {
        item = t;
    }

    public T get() {
        return item;
    }
}

class Goods {
    public void print() {
        System.out.println("我是物品");
    }
}

class Food extends Goods {
    public void print() {
        System.out.println("我是食物");
    }
}

class Fruit extends Food {
    public void print() {
        System.out.println("我是水果");
    }
}

class Apple extends Fruit {
    public void print() {
        System.out.println("我是苹果");
    }
}


class Gala extends Apple {
    public void print() {
        System.out.println("我是嘎啦苹果");
    }
}

class Meat extends Food {
    public void print() {
        System.out.println("我是肉");
    }
}
class SPlate extends Plate<Apple>{
    Apple t;
    public SPlate(Apple t){
        super(t);
    }
}








