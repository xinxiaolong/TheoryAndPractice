package com.example.fragment.theoryandpractice.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by xiaolong on 16/6/16.
 */
public class ScrollerLayout extends LinearLayout{

    /**
     * 用于完成滚动操作的实例
     */
    private Scroller mScroller;

    public ScrollerLayout(Context context) {
        super(context);
    }

    public ScrollerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller=new Scroller(context, new DecelerateInterpolator(2.0F));
    }

    public ScrollerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
       super.onLayout(changed,l,t,r,b);
    }


    public Scroller getScroller() {
        return mScroller;
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }

}
