package com.example.fragment.theoryandpractice.eventPractice;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by xiaolong.
 * on 2016-01-05 下午2:11.
 */
public class ChildViewGroup extends LinearLayout{

    public ChildViewGroup(Context context) {
        super(context);
    }

    public ChildViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //分发事件
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e(this.getClass().getName(),TouchEventUtil.getTouchAction("dispatchTouchEvent()",event));
        return super.dispatchTouchEvent(event);
    }

    //响应事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(this.getClass().getName(),TouchEventUtil.getTouchAction("onTouchEvent()",event));
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return super.onTouchEvent(event);
    }

    //阻断事件 返回 ture 事件不会再向下分发 后面的事件由自己的onTouchEvent(MotionEvent event)方法处理
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e(this.getClass().getName(), TouchEventUtil.getTouchAction("onInterceptTouchEvent()",ev));
        return super.onInterceptTouchEvent(ev);
    }
}
