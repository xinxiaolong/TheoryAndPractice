package com.example.fragment.theoryandpractice.eventPractice;

import android.view.MotionEvent;

/**
 * Created by xiaolong.
 * on 2016-01-05 下午2:15.
 */
public class TouchEventUtil {
    public static String getTouchAction(String method,MotionEvent event) {
        int actionId=event.getAction();
        String actionName = "Unknow:id=" + actionId;
        switch (actionId) {
            case MotionEvent.ACTION_DOWN:
                actionName = "ACTION_DOWN";
                break;
            case MotionEvent.ACTION_MOVE:
                actionName = "ACTION_MOVE";
                break;
            case MotionEvent.ACTION_UP:
                actionName = "ACTION_UP";
                break;
            case MotionEvent.ACTION_CANCEL:
                actionName = "ACTION_CANCEL";
                break;
            case MotionEvent.ACTION_OUTSIDE:
                actionName = "ACTION_OUTSIDE";
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                actionName = "ACTION_POINTER_DOWN";
                break;
        }
        return method+":"+actionName;
    }
}
