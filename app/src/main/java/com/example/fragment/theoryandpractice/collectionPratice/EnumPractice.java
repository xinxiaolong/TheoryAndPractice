package com.example.fragment.theoryandpractice.collectionPratice;

/**
 * Created by xiaolong on 16/8/3.
 */
public class EnumPractice {
    enum Color{
        RED,
        BLACK,
        BLUE,
        GRAY;
    }

    public static void main(String[] agre){
        for (Color color:Color.values()){
            System.out.println("name="+color.name()+"  ordinal="+color.ordinal());
        }

        System.out.print("枚举总个数="+Color.values().length);
    }
}
