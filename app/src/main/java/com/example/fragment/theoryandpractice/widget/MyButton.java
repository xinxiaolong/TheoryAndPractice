package com.example.fragment.theoryandpractice.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;

/**
 * Created by xiaolong.
 * on 2015-11-21 上午12:00.
 */
public class MyButton extends Button
{
    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width=getSuggestedMinimumWidth();
        int maxWidth=MeasureSpec.getSize(widthMeasureSpec);
        int mode=MeasureSpec.getMode(widthMeasureSpec);
        switch (mode){
            case MeasureSpec.AT_MOST:
                Log.v("View","AT_MOST");
                break;
            case MeasureSpec.EXACTLY:
                Log.v("View","EXACTLY");
                break;
            default:
                Log.v("View","UNSPECIFIED");
        }
        widthMeasureSpec=MeasureSpec.makeMeasureSpec(maxWidth,MeasureSpec.AT_MOST);
        mode=MeasureSpec.getMode(widthMeasureSpec);
        switch (mode){
            case MeasureSpec.AT_MOST:
                Log.v("View","AT_MOST");
                break;
            case MeasureSpec.EXACTLY:
                Log.v("View","EXACTLY");
                break;
            default:
                Log.v("View","UNSPECIFIED");
        }

        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
    }

}
