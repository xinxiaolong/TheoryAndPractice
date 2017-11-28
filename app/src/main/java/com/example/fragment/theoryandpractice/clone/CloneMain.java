package com.example.fragment.theoryandpractice.clone;

import com.example.fragment.theoryandpractice.clone.model.Book;
import com.example.fragment.theoryandpractice.clone.model.DeepCloneBox;
import com.example.fragment.theoryandpractice.clone.model.ShallowCloneBox;

/**
 * Created by xiaolong on 2017/11/10.
 * email：xinxiaolong123@foxmail.com
 *
 */

public class CloneMain {

    public static void main(String[] arg) throws CloneNotSupportedException {


        Book book=new Book("禅与摩托车修理艺术");



        System.out.println("**********************浅拷贝******************************");

        ShallowCloneBox sBox1=new ShallowCloneBox("书箱1",book);
        ShallowCloneBox sBox2=sBox1.clone();

        System.out.println("sBox1==sBox2 :"+(sBox1==sBox2));

        sBox1.boxName="sBox1";

        System.out.println("sBox1.boxName="+sBox1.boxName+"  sBox2.boxName="+sBox2.boxName);

        sBox1.book.bookName="书箱sBox1";
        System.out.println("sBox1.book.bookName="+sBox1.book.bookName+"  sBox2.book.bookName="+sBox2.book.bookName);


        System.out.println("**********************深拷贝******************************");
        Book deepBook=new Book("禅于摩托车");
        DeepCloneBox dBox1=new DeepCloneBox("书箱1",deepBook);
        DeepCloneBox dBox2=dBox1.clone();

        System.out.println("dBox1==dBox2 :"+(dBox1==dBox2));

        dBox1.boxName="dBox1";

        System.out.println("dBox1.boxName="+dBox1.boxName+"  dBox2.boxName="+dBox2.boxName);

        dBox1.book.bookName="书箱dBox1";

        System.out.println("dBox1.book.bookName="+dBox1.book.bookName+"  dBox2.book.bookName="+dBox2.book.bookName);


    }
}
