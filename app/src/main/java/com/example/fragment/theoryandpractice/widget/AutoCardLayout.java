package com.example.fragment.theoryandpractice.widget;

import android.content.Context;
import android.support.v4.app.FragmentTabHost;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by xiaolong.
 * on 2015-12-08 上午11:11.
 * 自定义瀑布流布局
 * 实现了子View的均衡分配
 */
public class AutoCardLayout extends ViewGroup{

    // 每行显示的列数
    int column = 2;
    // 每个Card的横向间距
    int margin = 10;

    //记录当前列的高度
    HashMap<Integer,Integer> lineHeightMap=new HashMap<>();
    //储存当前子View的所在的列 和 列的高度信息
    ArrayList<ChildViewLineInfo> childViewLineInfoList=new ArrayList<>();


    public AutoCardLayout(Context context) {
        super(context);
    }

    public AutoCardLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //重置记录的缓存数据
        initData();
        //获得父类的宽度
        int parentWidth=MeasureSpec.getSize(widthMeasureSpec);
        //给子View分配的宽度
        int childWidth=(parentWidth-(column+1)*margin)/column;
        //遍历子View进行宽高度的测量分配
        for (int i=0;i<getChildCount();i++){
            View childView=getChildAt(i);
            LayoutParams params=childView.getLayoutParams();
            //传入子View的宽度，获取子View的测量规格
            int childWidthSpec=MeasureSpec.makeMeasureSpec(childWidth,MeasureSpec.EXACTLY);
            int childHeightSpec=getChildMeasureSpec(heightMeasureSpec,0,params.height);
            childView.measure(childWidthSpec,childHeightSpec);
            //均衡的把子View分配到列
            balanceAddLine(childView);
        }

        //测量完所有子View的高度 再设置自己的布局宽高度
        setMeasuredDimension(parentWidth, getMaxLineHeight());
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        for (int i=0;i<getChildCount();i++){
            View childView=childViewLineInfoList.get(i).childView;
            //当前的是第几列
            int currentColumnIndex=childViewLineInfoList.get(i).currentColumnIndex;
            //当前列的高度
            int currentColumnheight=childViewLineInfoList.get(i).currentColumnheight;

            int childWidth=childView.getMeasuredWidth();
            int childHeight=childView.getMeasuredHeight();

            int top=currentColumnheight;
            int left=currentColumnIndex*childWidth+(currentColumnIndex+1)*margin;
            int right=left+childWidth;
            int bottom=top+childHeight;

            childView.layout(left, top, right, bottom);
        }
    }

    class ChildViewLineInfo{
        View childView;//当前View对象
        int currentColumnIndex;//当前列
        int currentColumnheight;//当前列的高度
        ChildViewLineInfo(View childView,int currentColumnIndex,int currentColumnheight){
            this.childView=childView;
            this.currentColumnIndex=currentColumnIndex;
            this.currentColumnheight=currentColumnheight;
        }
    }


    /**
     * 均衡的分配排版
     * 新加入的子View总是被列的高度最小的那一列
     * @param childView
     */
    private void balanceAddLine(View childView){
        int childHeight=childView.getMeasuredHeight();
        //得到最小高度的列
        int lineIndex=getMinLineHeightIndex();
        //得到这一列的高度
        int currentColumnheight=lineHeightMap.get(lineIndex)+margin;
        //记录下来 子View 被分配的列信息
        childViewLineInfoList.add(new ChildViewLineInfo(childView, lineIndex,currentColumnheight));
        //分配了子View之后 这个列的高度
        currentColumnheight=currentColumnheight+childHeight;
        //记录下这个列的新的高度
        lineHeightMap.put(lineIndex, currentColumnheight);
    }


    private void initData(){
        for (int i=0;i<column;i++){
            lineHeightMap.put(i, 0);
        }
        childViewLineInfoList.clear();
    }

    //获取高度最小的那个列
    private int getMinLineHeightIndex(){
        int mineLineIndex=0;
        int minHeight=lineHeightMap.get(0);
        for (int i=1;i<column;i++){
            int height=lineHeightMap.get(i);
            if(minHeight>height){
                mineLineIndex=i;
                minHeight=height;
            }
        }
        return mineLineIndex;
    }

    //获取列最大得高度
    private int getMaxLineHeight(){
        int maxHeight=lineHeightMap.get(0);
        for (int i=1;i<column;i++){
            int height=lineHeightMap.get(i);
           if(maxHeight<height){
               maxHeight=height;
           }
        }
        return maxHeight+margin;
    }


    //分发事件
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e(this.getClass().getName(), "dispatchTouchEvent()");
        return super.dispatchTouchEvent(event);
    }

    //响应事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(this.getClass().getName(),"onTouchEvent()");
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e(this.getClass().getName(), "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(this.getClass().getName(), "ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(this.getClass().getName(), "ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e(this.getClass().getName(), "ACTION_CANCEL");
                break;
        }
        return super.onTouchEvent(event);
    }

    //阻断事件
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e(this.getClass().getName(),"onInterceptTouchEvent()");
        return super.onInterceptTouchEvent(ev);
    }
}
