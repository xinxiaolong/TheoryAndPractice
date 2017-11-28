package com.example.fragment.theoryandpractice.eventPractice;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by xiaolong.
 * on 2016-01-05 下午2:11.
 */
public class ChildView extends View implements View.OnClickListener{

    Context mContext;
    public ChildView(Context context) {
        super(context);
        init(context);

    }

    public ChildView(Context context, AttributeSet attrs) {
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
        Log.e(this.getClass().getName(),TouchEventUtil.getTouchAction("dispatchTouchEvent()",event));
        return super.dispatchTouchEvent(event);
    }

    //响应事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(this.getClass().getName(),TouchEventUtil.getTouchAction("onTouchEvent()",event));
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //return false;
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                String action="UP";
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(mContext,"点击了子View",Toast.LENGTH_SHORT).show();
    }
}
