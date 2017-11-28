package com.example.fragment.theoryandpractice.collectionPratice;

import java.util.Date;
import java.util.LinkedList;

/**
 * Created by xiaolong.
 * on 2016-01-27 下午2:48.
 */
public class MyKey {

    public String keyName;

    public MyKey(){

    }

    public MyKey(String keyName){
        this.keyName=keyName;
    }
    @Override
    public String toString() {
        return keyName;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return keyName.hashCode();
    }

    public static void main(String [] arge){
        MyKey myKey=new MyKey();
        myKey.keyName="abc";
        myKey.change(myKey);
        System.out.println(myKey.keyName);

        MyKey myKey2=new MyKey();
        myKey2.keyName="ccc";

        System.out.print(myKey.hashCode()+":"+myKey2.hashCode()+"--"+myKey.equals(myKey2));
    }

    public void change(MyKey myKey){
        myKey.keyName="ccc";
    }
}
