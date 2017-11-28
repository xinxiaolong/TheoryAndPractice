package com.example.fragment.theoryandpractice.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fragment.theoryandpractice.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaolong on 16/6/21.
 */
public class StepProgressView extends LinearLayout{

    Context mContext;
    LinearLayout linear_img;
    AbsoluteLayout relative_text;
    public StepProgressView(Context context) {
        super(context);
        init(context);
    }

    public StepProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        mContext=context;
        initContentView();
    }

    private void initContentView(){
        linear_img=new LinearLayout(mContext);
        linear_img.setOrientation(LinearLayout.HORIZONTAL);
        addView(linear_img,getContentViewParams());


        relative_text =new AbsoluteLayout(mContext);
        LayoutParams params=getContentViewParams();
        addView(relative_text,params);

        initViewData(getSteps());
    }


    List<Step> steps;
    ImageView[] imageViews;
    public void initViewData(List<Step> steps){
        this.steps=steps;
        imageViews=new ImageView[steps.size()];
        for(int i=0;i<steps.size();i++){
            Step step=steps.get(i);
            addStepImage(i,step);
            addStepText(i,step);
        }
    }


    private ImageView addStepImage(int position, Step step){
        ImageView imageView=new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        if(position==0){
            linear_img.addView(imageView,getDefualtParams());
            imageView.setImageResource(step.complete? R.drawable.step_point_complete:R.drawable.step_point_complete);
        }else {
            linear_img.addView(imageView,getWeightParams());
            imageView.setImageResource(step.complete? R.drawable.step_point_line_complete:R.drawable.step_point_line);
        }
        int left=imageView.getRight();

        imageViews[position]=imageView;
        return imageView;
    }

    private void addStepText(int position,Step step){
        TextView textView=new TextView(mContext);
        int left=(ScreenWidth()/(steps.size()-1))*position;
        relative_text.addView(textView, getParams(left));
        textView.setText(step.name);
        textView.setTextColor(mContext.getResources().getColor(R.color.color_c7));
        textView.setTextSize(10);
    }

    private AbsoluteLayout.LayoutParams getParams(int x){
        AbsoluteLayout.LayoutParams layoutParams=new AbsoluteLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,x,0);
        return layoutParams;
    }

    private List<Step> getSteps(){
        List<Step> steps=new ArrayList<>();

        for (int i=0;i<5;i++){
            Step step=new Step();

            step.name="提交订单"+i;

            if(i>=4){
                step.complete=false;
            }else {
                step.complete=true;
            }

            steps.add(step);
        }

        return steps;
    }

    private LinearLayout.LayoutParams getContentViewParams(){
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        return params;
    }

    private LinearLayout.LayoutParams getDefualtParams(){
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        return params;
    }


    private LinearLayout.LayoutParams getWeightParams(){
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.weight=1;
        return params;
    }

    class Step{
        String name;
        boolean complete;
    }


    private int ScreenWidth(){
        if (mContext == null) {
            return 0;
        }
        int padding=mContext.getResources().getDimensionPixelSize(R.dimen.padding)*2;
        Display display = ((WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int w = display.getWidth();

        return w-padding;
    }
}
