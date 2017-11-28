package com.example.fragment.theoryandpractice.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.example.fragment.theoryandpractice.R;

/**
 * Created by xiaolong.
 * on 2015-11-21 上午12:39.
 */
public class MyTextView extends TextView{

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        String measureSpec="";

        int minimumWidth=getSuggestedMinimumWidth();
        int maxWidth=MeasureSpec.getSize(widthMeasureSpec);
        int mode=MeasureSpec.getMode(widthMeasureSpec);
        switch (mode){
            case MeasureSpec.AT_MOST:
                measureSpec="AT_MOST";
                break;
            case MeasureSpec.EXACTLY:
                measureSpec="EXACTLY";
                break;
            default:
                measureSpec="UNSPECIFIED";
        }
        Log.w("View", "Modify Before: MeasureSpec="+measureSpec+"  MinWidth="+minimumWidth+" MaxWidth="+maxWidth);


        maxWidth=maxWidth-10;
        minimumWidth=getSuggestedMinimumWidth();
        widthMeasureSpec=MeasureSpec.makeMeasureSpec(maxWidth, MeasureSpec.EXACTLY);
        mode=MeasureSpec.getMode(widthMeasureSpec);

        switch (mode){
            case MeasureSpec.AT_MOST:
                measureSpec="AT_MOST";
                break;
            case MeasureSpec.EXACTLY:
                measureSpec="EXACTLY";
                break;
            default:
                measureSpec="UNSPECIFIED";
        }

        Log.w("View", "Modify After: MeasureSpec="+measureSpec+"  MinWidth="+minimumWidth+" MaxWidth="+maxWidth);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.acquiesce_in_48x26);
        super.onDraw(canvas);

        Layout layout=getLayout();

        canvas.drawBitmap(bitmap, 0, 0, null);

        layout.draw(canvas);
    }
}
