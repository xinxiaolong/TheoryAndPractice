package com.example.fragment.theoryandpractice.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by xiaolong.
 * on 2016-01-04 下午2:26.
 */
public class CardImageView extends ImageView{


    public CardImageView(Context context) {
        super(context);
        init(context);
    }

    public CardImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        //setOnClickListener(new MyOnclickListnner());
        //this.setClickable(true);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e(this.getClass().getName(),"dispatchTouchEvent()");
        return super.dispatchTouchEvent(event);
    }

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

    class  MyOnclickListnner implements OnClickListener{
        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(),"点击了我",Toast.LENGTH_SHORT).show();
        }
    }
}
