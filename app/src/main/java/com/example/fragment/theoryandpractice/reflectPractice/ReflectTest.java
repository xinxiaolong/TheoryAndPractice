package com.example.fragment.theoryandpractice.reflectPractice;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by xiaolong on 2017/3/23.
 */

public class ReflectTest {


    static String className = "com.example.fragment.theoryandpractice.reflectPractice.ReflectTest";

    public static void main(String[] agre) {

        try {

            Class tc = Class.forName(className);
            ReflectTest reflectTest = (ReflectTest) tc.newInstance();
            reflectTest.print();


            A a = new A("名字一");
            Class cls = a.getClass();

            System.out.println("反射类中所有公有的属性");
            Field[] fb = cls.getFields();
            for (int j = 0; j < fb.length; j++) {
                Class cl = fb[j].getType();
                System.out.println("fb:" + cl);

            }

            System.out.println("反射类中所有的属性");
            Field[] fa = cls.getDeclaredFields();
            for (int j = 0; j < fa.length; j++) {
                Class cl = fa[j].getType();
                System.out.println("fa:" + cl);
            }

            System.out.println("反射类的构造函数");
            Constructor[] theConstructors = cls.getConstructors();
            for (int i = 0; i < theConstructors.length; i++) {
                Class[] parameterTypes = theConstructors[i].getParameterTypes();
                System.out.print(className + "(");
                for (int j = 0; j < parameterTypes.length; j++)
                    System.out.print(parameterTypes[j].getName() + " ");
                System.out.println(")");
            }


            System.out.println("反射类的所有方法(不包括继承的方法)");
            //getMethods()返回某个类的所有公用（public）方法包括其继承类的公用方法，当然也包括它所实现接口的方法。
            //getDeclaredMethods()对象表示的类或接口声明的所有方法，包括公共、保护、默认（包）访问和私有方法，但不包括继承的方法。当然也包括它所实现接口的方法。
            Method[] m = cls.getDeclaredMethods();
            for (int i = 0; i < m.length; i++) {
                //输出方法的返回类型
                System.out.print(m[i].getReturnType().getName());
                //输出方法名
                System.out.print(" " + m[i].getName() + "(");
                //获取方法的参数
                Class[] parameterTypes = m[i].getParameterTypes();
                for (int j = 0; j < parameterTypes.length; j++) {
                    System.out.print(parameterTypes[j].getName());
                    if (parameterTypes.length > j + 1) {
                        System.out.print(",");
                    }
                }
                System.out.println(")");
            }


            System.out.println("反射类调用指定方法");
            Method method=cls.getMethod("printName");
            //method.invoke(a,null);

            System.out.println("反射类调用指定私有方法");
            Method method1=cls.getDeclaredMethod("run");
            method1.setAccessible(true);
            //method1.invoke(a,null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void print() {
        System.out.println("我是 ReflectTest 类");
    }
}

class A {

    public String name = "默认名字";
    private int num = 0;


    public A() {

    }

    public A(String name) {
        this.name = name;
    }


    public void printName() {
        System.out.println("printName  " + name);
    }

    public void printName(String name) {
        System.out.println("printName(String name)" + name);
    }

    private void run() {
        System.out.println("私有的run方法");
    }
}
