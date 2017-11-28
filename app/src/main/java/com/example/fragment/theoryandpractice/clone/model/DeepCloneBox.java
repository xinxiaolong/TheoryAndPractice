package com.example.fragment.theoryandpractice.clone.model;

/**
 * Created by xiaolong on 2017/11/10.
 * email：xinxiaolong123@foxmail.com
 */

public class DeepCloneBox implements Cloneable{

    public String boxName;
    public Book book;

    public DeepCloneBox(String boxName,Book book){
        this.book=book;
        this.boxName=boxName;
    }

    @Override
    public DeepCloneBox clone() throws CloneNotSupportedException {
        DeepCloneBox deepCloneBox=(DeepCloneBox)super.clone();
        Book book=deepCloneBox.book.clone();
        deepCloneBox.book=book;
        return deepCloneBox;
    }
}
