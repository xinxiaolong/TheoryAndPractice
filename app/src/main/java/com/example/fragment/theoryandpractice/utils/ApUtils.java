package com.example.fragment.theoryandpractice.utils;

import android.widget.Toast;

import com.example.fragment.theoryandpractice.PracticeApplication;

/**
 * Created by xiaolong.
 * on 2016-01-14 下午12:23.
 */
public class ApUtils {
    public static void showToast(Object text){
        Toast.makeText(PracticeApplication.getContext(),text+"",Toast.LENGTH_SHORT).show();
    }



    public static void println(Object object){
        System.out.println(object+"");
    }
}
