package com.example.fragment.theoryandpractice.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.Scroller;

/**
 * Created by xiaolong.
 * on 2016-01-04 下午3:09.
 */
public class PullToRefreshScrollView extends ScrollView{

    Scroller scroller;
    View innerView;
    public PullToRefreshScrollView(Context context) {
        super(context);
        init(context);
    }

    public PullToRefreshScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        scroller=new Scroller(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        innerView=getChildAt(0);
    }

    //分发事件
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e(this.getClass().getName(), "dispatchTouchEvent()");
        boolean dispatch=super.dispatchTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                onTouchEvent(event);
                break;
        }
        return dispatch;
    }

    int downY=0;
    //响应事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(this.getClass().getName(), "onTouchEvent()");
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                downY=(int)event.getY();
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

    //阻断事件 返回 ture 事件不会再向下分发 后面的事件由自己的onTouchEvent(MotionEvent event)方法处理
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e(this.getClass().getName(), "onInterceptTouchEvent()");
//        switch (ev.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                return false;
//            case MotionEvent.ACTION_MOVE:
//                return false;
//            case MotionEvent.ACTION_UP:
//                return false;
//        }
        return super.onInterceptTouchEvent(ev);
    }


    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX,
                                   int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {

        final boolean returnValue = super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX,
                scrollRangeY, maxOverScrollX, 400, isTouchEvent);

        return returnValue;
    }

}
