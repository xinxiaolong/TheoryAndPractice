package com.example.fragment.theoryandpractice.clone.model;

/**
 * Created by xiaolong on 2017/11/10.
 * email：xinxiaolong123@foxmail.com
 */

public class Book implements Cloneable{

    public String bookName;
    public Book(String bookName){
        this.bookName=bookName;
    }

    @Override
    public Book clone() throws CloneNotSupportedException {
        return (Book)super.clone();
    }
}
