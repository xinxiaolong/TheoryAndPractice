package com.example;

public class MyClass {

    public static void main(String[] agr){

        Integer num1=100;
        Integer num2=100;
        System.out.println(num1==num2);

        Integer num3=400;
        Integer num4=400;
        System.out.println(num3==num4);

        Integer num5=1000;
        Integer num6=num5;
        System.out.println(num5==num6);

        int num7=400;
        int num8=400;
        System.out.println(num7==num8);

        Integer num9=0;
        System.out.println(num3==num4+num9);

        String str1 = "str";
        String str2 = "ing";

        String str3 = "str" + "ing";
        String str4 = str1 + str2;
        System.out.println(str3 == str4);//false

        String str5 = "string";
        System.out.println(str3 == str5);//true

        char c='\u4e01';
        System.out.println(c);//true

    }
}
