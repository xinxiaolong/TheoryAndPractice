package com.example.fragment.theoryandpractice.clone.model;

/**
 * Created by xiaolong on 2017/11/10.
 * email：xinxiaolong123@foxmail.com
 */

public class ShallowCloneBox implements Cloneable{

    public String boxName;
    public Book book;

    public ShallowCloneBox(String boxName,Book book){
        this.book=book;
        this.boxName=boxName;
    }

    @Override
    public ShallowCloneBox clone() throws CloneNotSupportedException {
        return (ShallowCloneBox)super.clone();
    }

}
