package com.example.fragment.theoryandpractice.utils;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.example.fragment.theoryandpractice.PracticeApplication;

public class DensityHelper {  
  
    /** 
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素) 
     */  
    public static int dip2px(Context context, float dpValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (dpValue * scale + 0.5f);  
    }  
  
    /** 
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp 
     */  
    public static int px2dip(Context context, float pxValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (pxValue / scale + 0.5f);  
    }  
    
    public static int getWidthOfTheScreen(){
    	WindowManager wm = (WindowManager)PracticeApplication.getContext().getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }

    public static int getHeightOfTheScreen(){
        WindowManager wm = (WindowManager)PracticeApplication.getContext().getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        return height;
    }

   //获取屏幕的尺寸大小
    public static double getScreenSize(Context context){
        DisplayMetrics metrics =context. getResources().getDisplayMetrics();
        float density = metrics.density;
        float xdpi = metrics.xdpi;
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        double z = Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2));
        double size = (z / (xdpi * density));
        return size;
    }

}