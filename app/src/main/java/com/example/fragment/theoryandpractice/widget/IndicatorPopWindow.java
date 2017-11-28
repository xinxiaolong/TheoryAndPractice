package com.example.fragment.theoryandpractice.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.example.fragment.theoryandpractice.R;

/**
 * Created by xiaolong on 2017/5/28.
 */

public class IndicatorPopWindow extends PopupWindow{


    Context mContext;
    View contentView;
    public IndicatorPopWindow(Context context){
        mContext=context;
        initContentView(context);
    }

    private void initContentView(Context context){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView=inflater.inflate(R.layout.pop_indicator_popwindow_layout, null);
        setContentView(contentView);

        setWidth(400);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setOutsideTouchable(true);
        ColorDrawable dw = new ColorDrawable(0000000000);
        setBackgroundDrawable(dw);
        update();
    }
    public void showAsDropDown(View targetView){
       super.showAsDropDown(targetView);
    }
}
