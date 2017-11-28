package com.example.fragment.theoryandpractice.polymorphismPractice;

/**
 * Created by xiaolong on 2017/3/25.
 */

public class ExtendsTest {


    public static void main(String[] arge){

        Snake snake=new Snake("xiao");

    }
}


class Animal{


    public Animal(){
        System.out.println("Animal构造方法");
    }
    public Animal(String name){
        System.out.println("Animal(String name)构造方法");
    }
}

class Snake extends Animal{

    String name;
    public Snake(String name){
        super();
    }
}
