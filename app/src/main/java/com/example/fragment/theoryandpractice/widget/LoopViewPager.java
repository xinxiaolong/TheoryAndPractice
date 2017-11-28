package com.example.fragment.theoryandpractice.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


/**
 * Created by xiaolong on 2017/3/24.
 */

public class LoopViewPager extends ViewPager {


    private List<ImageView> listviews;
    private int[] mPics;


    public LoopViewPager(Context context) {
        super(context);
    }

    public LoopViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public void init(){
       addOnPageChangeListener(onPageChangeListener);
    }



    //暂时定义成list<int>
    public void setPagerAdapterData(int[] pics){
        this.mPics=pics;
        initListView(pics.length);
        this.mPics=new int[pics.length+2];

        for (int i=0;i<mPics.length;i++){
            if(i==0){
                mPics[i]=pics[pics.length-1];
            }else if(i==mPics.length-1){
                mPics[i]=pics[0];
            }else {
                mPics[i]=pics[i-1];
            }
        }

        setAdapter(pagerAdapter);
    }

    private void initListView(int length){
        listviews=new ArrayList<>();

        for (int i = 0; i < length+2; i++) {
            ImageView imageView = new ImageView(getContext());
            ViewGroup.LayoutParams viewPagerImageViewParams = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(viewPagerImageViewParams);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            listviews.add(imageView);
        }
    }


    OnPageChangeListener onPageChangeListener=new OnPageChangeListener() {

        int mPosition;
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            mPosition=position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == SCROLL_STATE_IDLE) {
                int pageIndex = mPosition;
                if (mPosition == 0) {
                    // 当视图在第一个时，将页面号设置为图片的最后一张。
                    pageIndex=mPics.length-2;
                } else if (mPosition ==mPics.length -1) {
                    // 当视图在最后一个是,将页面号设置为图片的第一张。
                    pageIndex = 1;
                }

                if (mPosition != pageIndex) {
                    setCurrentItem(pageIndex, false);
                    return;
                }
            }
        }
    };


    PagerAdapter pagerAdapter=new PagerAdapter() {
        @Override
        public int getCount() {
            return mPics.length;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int i) {
//            if (i == 0) {
//                listviews.get(i).setImageResource(mPics[mPics.length-1]);
//            } else if (i == (listviews.size() - 1)) {
//                listviews.get(i).setImageResource(mPics[0]);
//            } else {
//                listviews.get(i).setImageResource(mPics[i-1]);
//            }
            listviews.get(i).setImageResource(mPics[i]);
            container.addView(listviews.get(i));
            return listviews.get(i);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ImageView view = listviews.get(position);
            container.removeView(view);
            view.setImageBitmap(null);
        }
    };
}
