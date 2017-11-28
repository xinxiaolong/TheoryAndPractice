package com.example.fragment.theoryandpractice.takePhotoPractice.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


import com.example.fragment.theoryandpractice.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by wangzheng on 2015/12/11.
 */
public class CameraPictureAdapter extends AdapterBase<String> {
    private Context context = null;
    private ViewHolder viewHolder;
    private ImageLoader imageLoader=ImageLoader.getInstance();
    private Animation animation;

    public CameraPictureAdapter(Context context) {
        super(context);
        this.context = context;
        animation= AnimationUtils.loadAnimation(context, R.anim.rock);
    }

    @Override
    protected int getConvertViewId() {
        return R.layout.camera_item_layout;
    }


    @Override
    protected Object getCustomView() {
        viewHolder=new ViewHolder();
        return viewHolder;
    }

    @Override
    protected void setCustomView(Object customView) {
        viewHolder=(ViewHolder)customView;
    }

    @Override
    protected void setViewData(int position) {
        imageLoader.displayImage("file://" + getItem(position),viewHolder.imageview);
        viewHolder.imageview.startAnimation(animation);
    }

    class ViewHolder {
        @InjectView(R.id.imageview)
        ImageView imageview;
        @InjectView(R.id.delete)
        ImageView delete;
    }
}
