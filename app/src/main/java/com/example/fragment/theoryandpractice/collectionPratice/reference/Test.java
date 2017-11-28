package com.example.fragment.theoryandpractice.collectionPratice.reference;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by xiaolong on 2016/12/7.
 */

public class Test {

    public static int softReferenceCount = 0;
    public static int count = 0;

    public static void main(String[] arge) {

//        Box box1=new Box("软引用盒子");
//        SoftReference<Box> softReference = new SoftReference(box1);
//
//        Box box2=new Box("弱引用盒子");
//        WeakReference<Box> boxWeakReference=new WeakReference(box2);
//
//        box1=null;
//        box2=null;
//
//        System.gc();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        //这个例子明显看出内存比较大的时候WeakReference对象先被回收掉
        ArrayList<SoftReference<Box>> softReferenceArrayList = new ArrayList<>();
        ArrayList<WeakReference<Box>> weakReferenceArrayList = new ArrayList<>();

        ArrayList<Box> boxArrayList = new ArrayList<>();

        for (int i = 0; i < 20000; i++) {

            Box box = new Box("软引用生产的第:" + i);
            box.isSoftReference = true;
//          SoftReference<Box> softReferencex = new SoftReference(box);
//          softReferenceArrayList.add(softReferencex);

            WeakReference<Box> weakReferencex = new WeakReference(box);
            weakReferenceArrayList.add(weakReferencex);

            Box temp = new Box("生产的第:" + i);
            temp.isSoftReference = false;
            boxArrayList.add(temp);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("软引用回收数量" + softReferenceCount + "   普通的对象回收数量" + count);
        }
    }
}

class Box {
    String name;
    boolean isSoftReference = false;
    byte[] b = new byte[4000 * 1024];

    public Box(String name) {
        this.name = name;
    }

    protected void finalize() {
        if (isSoftReference) {
            Test.softReferenceCount++;
        } else {
            Test.count++;
        }
        System.out.println("Finalizing Box " + name + "    " + "软引用回收数量" + Test.softReferenceCount + "   普通的对象回收数量" + Test.count);
    }

}
