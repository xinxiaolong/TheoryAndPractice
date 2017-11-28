package com.example.fragment.theoryandpractice.genericity;

import com.example.fragment.theoryandpractice.genericity.model.Animal;
import com.example.fragment.theoryandpractice.genericity.model.Bird;
import com.example.fragment.theoryandpractice.genericity.model.Car;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by xiaolong on 2016/12/6.
 */

//有许多原因促成了泛型的出现，而最引人注意的一个原因，就是为了创建容器类
public class GenericityConcept {
    public static void main(String[] arge) {
        //*************************************编译器产生的作用*******************************//
        //下面代码证明 Java中的泛型，只在编译阶段有效
        ArrayList<Integer> a = new ArrayList<Integer>();
        a.add(12);
        a.add(13);
        Class c = a.getClass();
        int num = a.get(0) + a.get(1);
        try {
            Method method = c.getMethod("add", Object.class);
            method.invoke(a, "12.f");
            System.out.println(a);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //成功编译过后的class文件中是不包含任何泛型信息的
        //编译后的样子
        ArrayList var1 = new ArrayList();
        var1.add(Integer.valueOf(12));
        var1.add(Integer.valueOf(13));
        Class var2 = var1.getClass();
        int var3 = ((Integer) var1.get(0)).intValue() + ((Integer) var1.get(1)).intValue();
        try {
            Method var4 = var2.getMethod("add", new Class[]{Object.class});
            var4.invoke(var1, new Object[]{"12.f"});
            System.out.println(var1);
        } catch (Exception var4) {
            var4.printStackTrace();
        }


        //***************************************通配符**************************************//
        // 如果一个方法参数要接受相同集合,不同泛型的时候。就需要通配符?来做通配
        ArrayList<String> strList = new ArrayList<>();
        strList.add("1");
        ArrayList<Integer> intList = new ArrayList<>();
        intList.add(1);

        getData(strList);
        getData(intList);

        //**************************************上下边界*************************************//
        ArrayList<Animal> animalArrayList = new ArrayList<>();
        animalArrayList.add(new Animal());
        ArrayList<Bird> birdArrayList = new ArrayList<>();
        birdArrayList.add(new Bird());
        ArrayList<Car> carArrayList = new ArrayList<>();
        carArrayList.add(new Car());

        getData(carArrayList);
    }

    //这里的ArrayList<?> ?是做一个类型的通配标示,告诉编辑器,接受任何类型
    public static void getData(ArrayList arrayList) {
        System.out.println(arrayList.get(0));
    }
}

