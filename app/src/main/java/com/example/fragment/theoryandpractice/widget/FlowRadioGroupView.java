package com.example.fragment.theoryandpractice.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import java.util.HashMap;

/**
 * Created by xiaolong.
 * on 2016-01-04 上午10:51.
 */
public class FlowRadioGroupView extends RadioGroup{


    HashMap<Integer,Rect> childLayoutRectMap=new HashMap<>();

    public FlowRadioGroupView(Context context) {
        super(context);
        init();
    }

    public FlowRadioGroupView(Context context,AttributeSet attrs){
        super(context, attrs);
        init();
    }


    private void init(){
        setOrientation(VERTICAL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(getChildCount()==0){
            return;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        initChildLayoutRectMap();
        setMeasuredDimension();
    }


    private void setMeasuredDimension(){
        RadioGroup.LayoutParams layoutParams=getChildLayoutParams(getChildAt(0));
        Rect lastChildRect=childLayoutRectMap.get(childLayoutRectMap.keySet().size()-1);
        setMeasuredDimension(getMeasuredWidth(), lastChildRect.bottom+layoutParams.bottomMargin);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for(int i=0;i<getChildCount();i++){
            Rect rect=childLayoutRectMap.get(i);
            View child=getChildAt(i);
            child.layout(rect.left,rect.top,rect.right,rect.bottom);
        }
    }



    private void initChildLayoutRectMap(){

        int parentViewWidth=getMeasuredWidth();
        int curChildTop=0; //当前子View顶部位于父布局的距离
        int curChildLeft=0;//当前子View左部位于父布局的距离
        int childHeight=((View)getChildAt(0)).getMeasuredHeight();

        for(int i=0;i<getChildCount();i++){
            View child=getChildAt(i);

            int childLeft;
            int childTop;
            int childRight;
            int childBottom;

            final RadioGroup.LayoutParams lp =(RadioGroup.LayoutParams) child.getLayoutParams();
            childLeft=curChildLeft+lp.leftMargin;
            childTop=curChildTop+lp.topMargin;

            childRight=getChildRight(curChildLeft, child);
            childBottom=getChildBottom(childHeight,curChildTop,child);
            //如果这个子View在当前行的第一位，并且宽度排版会超出父布局宽度 则做换行处理
            if(curChildLeft!=0&&childRight>parentViewWidth){
                curChildLeft=0;
                curChildTop=childBottom;

                childLeft=curChildLeft+lp.leftMargin;
                childTop=curChildTop+lp.topMargin;
            }

            childRight = getChildRight(curChildLeft, child);
            childBottom=getChildBottom(childHeight, curChildTop, child);
            Rect rect=new Rect(childLeft,childTop,childRight,childBottom);
            childLayoutRectMap.put(i,rect);
            curChildLeft=childRight;
        }
    }

    private int getChildRight(int curChildLeft,View child){
        RadioGroup.LayoutParams lp =(RadioGroup.LayoutParams) child.getLayoutParams();
        return curChildLeft+child.getMeasuredWidth()+lp.rightMargin;
    }

    private int getChildBottom(int childHeight,int curChildTop,View child){
        RadioGroup.LayoutParams lp =(RadioGroup.LayoutParams) child.getLayoutParams();
        return curChildTop+childHeight+lp.bottomMargin;
    }

    private RadioGroup.LayoutParams getChildLayoutParams(View child){
        return (RadioGroup.LayoutParams) child.getLayoutParams();
    }
}
