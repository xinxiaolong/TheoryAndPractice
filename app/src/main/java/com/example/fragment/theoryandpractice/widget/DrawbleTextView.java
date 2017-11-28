package com.example.fragment.theoryandpractice.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by xiaolong.
 * on 2015-12-18 下午5:13.
 */
public class DrawbleTextView  extends TextView{

    public DrawbleTextView(Context context) {
        super(context);
    }

    public DrawbleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
    }
}
