package com.example.fragment.theoryandpractice.collectionPratice;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by xiaolong on 2017/1/20.
 */

public class ArrayMapTest {

    public static void main(String[] args){
        ArrayMap<AspectRatio, String> mRatios = new ArrayMap<>();
        AspectRatio a1=new AspectRatio(100,1);
        AspectRatio a2=new AspectRatio(100,10);
        AspectRatio a3=new AspectRatio(100,14);
        AspectRatio a4=new AspectRatio(100,19);
        AspectRatio a5=new AspectRatio(100,28);
        AspectRatio a6=new AspectRatio(100,15);


        mRatios.put(a2,"");
        mRatios.put(a1,"");
        mRatios.put(a5,"");
        mRatios.put(a3,"");
        mRatios.put(a4,"");
        mRatios.put(a6,"");

        Set<AspectRatio> set=mRatios.keySet();
        TreeSet<AspectRatio> treeSet=new TreeSet<>(set);

        Iterator<AspectRatio> iterator=treeSet.iterator();

        while (iterator.hasNext()){
            AspectRatio aspectRatio=iterator.next();
            System.out.println(aspectRatio.getX()+":"+aspectRatio.getY());
        }

        AspectRatio aspectRatio=new AspectRatio(100,13);

        getRecentAspectRatio(aspectRatio,mRatios);

    }


    private static AspectRatio getRecentAspectRatio(AspectRatio aspectRation,ArrayMap<AspectRatio, String> mRatios){
        Set<AspectRatio> aspectRatioSet=mRatios.keySet();
        SortedSet<AspectRatio> aspectRatios=new TreeSet<>(aspectRatioSet);
        Iterator<AspectRatio> iterator=aspectRatios.iterator();

        if(mRatios.size()==1){
            return iterator.next();
        }
        AspectRatio aspectRatio1=iterator.next();
        AspectRatio aspectRatio2=iterator.next();
        float recent1=Math.abs(aspectRation.toFloat()-aspectRatio1.toFloat());
        float recent2=Math.abs(aspectRation.toFloat()-aspectRatio2.toFloat());

        while (recent1>recent2){
            aspectRatio1=aspectRatio2;
            if(iterator.hasNext()){
                aspectRatio2=iterator.next();
            }else {
                break;
            }
            recent1=Math.abs(aspectRation.toFloat()-aspectRatio1.toFloat());
            recent2=Math.abs(aspectRation.toFloat()-aspectRatio2.toFloat());
        }

        System.out.println("最为接近的是"+aspectRatio1.getX()+":"+aspectRatio1.getY());
        return aspectRatio1;
    }

    private static AspectRatio getRecentAspectRatio(AspectRatio aspectRation,SortedSet<AspectRatio> aspectRatios){
        Iterator<AspectRatio> iterator=aspectRatios.iterator();
        if(aspectRatios.size()==1){
            return iterator.next();
        }
        AspectRatio aspectRatio1=iterator.next();
        AspectRatio aspectRatio2=iterator.next();
        float recent1=Math.abs(aspectRation.toFloat()-aspectRatio1.toFloat());
        float recent2=Math.abs(aspectRation.toFloat()-aspectRatio2.toFloat());

        while (recent1>recent2){
            aspectRatio1=aspectRatio2;
            if(iterator.hasNext()){
                aspectRatio2=iterator.next();
            }else {
                break;
            }
            recent1=Math.abs(aspectRation.toFloat()-aspectRatio1.toFloat());
            recent2=Math.abs(aspectRation.toFloat()-aspectRatio2.toFloat());
        }
        return aspectRatio1;
    }
}

class AspectRatio implements Comparable<AspectRatio>{
    private  int mX;//代表宽度
    private  int mY;//代表高度

    protected AspectRatio(int x,int y) {
        mX = x;
        mY = y;
    }

    public int getX(){
        return mX;
    }

    public int getY(){
        return mY;
    }

    @Override
    public int compareTo(@NonNull AspectRatio another) {
        if (equals(another)) {
            return 0;
        } else if (toFloat() - another.toFloat() > 0) {
            return 1;
        }
        return -1;
    }
    public float toFloat() {
        return (float) mY/mX ;
    }
}
