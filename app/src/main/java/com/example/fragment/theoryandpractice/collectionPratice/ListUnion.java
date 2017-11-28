package com.example.fragment.theoryandpractice.collectionPratice;

import java.util.ArrayList;

/**
 * Created by xiaolong on 16/9/8.
 */
public class ListUnion {

    public static void main(String[] arge){
        ArrayList<String> listA=new ArrayList<>();
        listA.add("1");
        listA.add("2");
        listA.add("3");
        listA.add("4");

        ArrayList<String> listB=new ArrayList<>();
        listB.add("1");
        listB.add("2");
        listB.add("5");
        listB.add("6");

        listA.retainAll(listB);

        for (String str:listA){
            System.out.println(str);
        }
    }
}
