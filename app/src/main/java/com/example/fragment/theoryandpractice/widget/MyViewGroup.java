package com.example.fragment.theoryandpractice.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by xiaolong.
 * on 2015-12-01 下午3:01.
 */
public class MyViewGroup extends ViewGroup{

    private int mDividerWidth=0;
    private int mDividerHeight=0;

    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        for (int i=0;i<getChildCount();i++){
            View childView=getChildAt(i);
            LayoutParams params=childView.getLayoutParams();
            childView.measure(getChildMeasureSpec(widthMeasureSpec,0,params.width),getChildMeasureSpec(heightMeasureSpec,0,params.height));
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width=getWidth();
        int lastChildViewHeight=0;
        mDividerWidth=0;
        mDividerHeight=0;
        for (int i=0;i<getChildCount();i++){
             View childView=getChildAt(i);
             int childWidth=childView.getMeasuredWidth();
             int childHeight=childView.getMeasuredHeight();
             if(mDividerWidth+childWidth>width){
                 mDividerWidth=0;
                 mDividerHeight=mDividerHeight+lastChildViewHeight;
             }
             int left=mDividerWidth;
             int top=mDividerHeight;
             int right=left+childWidth;
             int bottom=top+childHeight;
             childView.layout(left,top,right,bottom);
             mDividerWidth=right+4;
             lastChildViewHeight=childHeight;
         }
    }
}
