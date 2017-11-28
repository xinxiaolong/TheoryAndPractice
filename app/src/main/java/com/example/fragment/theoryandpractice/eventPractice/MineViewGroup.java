package com.example.fragment.theoryandpractice.eventPractice;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by xiaolong.
 * on 2016-01-05 下午2:12.
 */
public class MineViewGroup extends LinearLayout implements View.OnClickListener{

    Context mContext;
    public MineViewGroup(Context context) {
        super(context);
        init(context);
    }

    public MineViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        this.mContext=context;
        setOnClickListener(this);
    }

    //分发事件
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e(this.getClass().getName(), TouchEventUtil.getTouchAction("dispatchTouchEvent()", event));
        return  super.dispatchTouchEvent(event);
    }


    //响应事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(this.getClass().getName(),TouchEventUtil.getTouchAction( "onTouchEvent()",event));
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
        Log.e(this.getClass().getName(),TouchEventUtil.getTouchAction("onInterceptTouchEvent()",ev));
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(mContext, "点击了父View", Toast.LENGTH_SHORT).show();
    }
}
