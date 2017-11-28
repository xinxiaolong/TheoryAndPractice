package com.example.fragment.theoryandpractice.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.widget.ImageView;

import java.text.DecimalFormat;

/**
 * Created by xiaolong on 16/7/30.
 */
public class WaterMarkUtil {

    public static Bitmap addWatermarkBitmap(Bitmap bitmap,String str) {
        int destWidth = bitmap.getWidth();   //此处的bitmap已经限定好宽高
        int destHeight = bitmap.getHeight();

        Log.v("tag","width = " + destWidth+" height = "+destHeight);

        Bitmap icon = Bitmap.createBitmap(destWidth, destHeight, Bitmap.Config.ARGB_8888); //定好宽高的全彩bitmap
        Canvas canvas = new Canvas(icon);//初始化画布绘制的图像到icon上

        Paint photoPaint = new Paint(); //建立画笔
        photoPaint.setDither(true); //获取跟清晰的图像采样
        photoPaint.setFilterBitmap(true);//过滤一些

        Rect src = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());//创建一个指定的新矩形的坐标
        Rect dst = new Rect(0, 0, destWidth, destHeight);//创建一个指定的新矩形的坐标
        canvas.drawBitmap(bitmap, src, dst, photoPaint);//将photo 缩放或则扩大到 dst使用的填充区photoPaint

        Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DEV_KERN_TEXT_FLAG);//设置画笔
        textPaint.setTextSize(destWidth/20);//字体大小
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);//采用默认的宽度
        textPaint.setAntiAlias(true);  //抗锯齿
        textPaint.setStrokeWidth(3);
        textPaint.setAlpha(15);
        //textPaint.setStyle(Paint.Style.STROKE); //空心
        textPaint.setColor(Color.RED);//采用的颜色
        textPaint.setShadowLayer(1f, 0f, 3f, Color.LTGRAY);
      //  textPaint.setShadowLayer(3f, 1, 1,imageView.getContext().getResources().getColor(android.R.color.white));//影音的设置

        float textWidth = textPaint.measureText(str);//文字的总宽度
        Rect rect=new Rect();
        textPaint.getTextBounds("测", 0, 1, rect);
        float textHeight=rect.height();

        canvas.drawText(str,textWidth/2+7, destHeight-textHeight/2, textPaint);//绘制上去字，开始未知x,y采用那只笔绘制
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();
        bitmap.recycle();
        return  icon;
        //return FileUtil.getInstance().saveMyBitmap(icon,String.valueOf(new Date().getTime())); //保存至文件
    }


    public static double toFormatVolumeValue(double volumeValue) {
        double formatVolumeValue = 0.0;
        try {
            DecimalFormat df = new DecimalFormat("######0.0");
            formatVolumeValue = Double.parseDouble(df.format(volumeValue));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return formatVolumeValue;
    }


    public static void main(String[] args){
        System.out.println(toFormatVolumeValue(9.91234));
    }

}
