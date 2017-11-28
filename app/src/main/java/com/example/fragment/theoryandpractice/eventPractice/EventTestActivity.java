package com.example.fragment.theoryandpractice.eventPractice;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.fragment.theoryandpractice.R;

/**
 * Created by xiaolong.
 * on 2016-01-05 下午3:01.
 */
public class EventTestActivity extends Activity{


    ViewGroup viewGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_layout);
        viewGroup=(ViewGroup) findViewById(R.id.viewGroup);

       // viewGroup.setEnabled(false);

        setOnTouchEvent(viewGroup);
    }


    private void setOnTouchEvent(View view){
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e(this.getClass().getName(),"setOnTouchEvent:"+TouchEventUtil.getTouchAction( "onTouchEvent()",event));
                return false;
            }
        });
    }
    //分发事件
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        Log.e(this.getClass().getName(), TouchEventUtil.getTouchAction("dispatchTouchEvent()", event));

        boolean dispatch=super.dispatchTouchEvent(event);
        Log.w(this.getClass().getName(),dispatch+"");
        return dispatch;
    }
    //响应事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(this.getClass().getName(), TouchEventUtil.getTouchAction("onTouchEvent()", event));
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
}
