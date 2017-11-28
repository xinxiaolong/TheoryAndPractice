package com.example.fragment.theoryandpractice.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.fragment.theoryandpractice.R;

import java.math.BigDecimal;

/**
 * Created by xiaolong.
 * on 2016-03-01 下午4:58.
 */
public class MyRatingBar extends LinearLayout {
    private boolean mClickable;
    private int starCount;
    private OnRatingChangeListener onRatingChangeListener;
    private float starImageSize;
    private Drawable starEmptyDrawable;
    private Drawable starFillDrawable;
    private Drawable starHalfDrawable;

    public void setStarHalfDrawable(Drawable starHalfDrawable) {
        this.starHalfDrawable = starHalfDrawable;
    }


    public void setOnRatingChangeListener(OnRatingChangeListener onRatingChangeListener) {
        this.onRatingChangeListener = onRatingChangeListener;
    }

    public void setmClickable(boolean clickable) {
        this.mClickable = clickable;
    }

    public void setStarFillDrawable(Drawable starFillDrawable) {
        this.starFillDrawable = starFillDrawable;
    }

    public void setStarEmptyDrawable(Drawable starEmptyDrawable) {
        this.starEmptyDrawable = starEmptyDrawable;
    }

    public void setStarImageSize(float starImageSize) {
        this.starImageSize = starImageSize;
    }


    /**
     * @param context
     * @param attrs
     */
    public MyRatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.HORIZONTAL);
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.RatingBar);
        starImageSize = mTypedArray.getDimension(R.styleable.RatingBar_starImageSize, 20);
        starCount = mTypedArray.getInteger(R.styleable.RatingBar_starCount, 5);
        starEmptyDrawable = mTypedArray.getDrawable(R.styleable.RatingBar_starEmpty);
        starFillDrawable = mTypedArray.getDrawable(R.styleable.RatingBar_starFill);
        mClickable = mTypedArray.getBoolean(R.styleable.RatingBar_clickable, true);
        for (int i = 0; i < starCount; ++i) {
            ImageView imageView = getStarImageView(context, attrs);
            imageView.setOnClickListener(
                    new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mClickable) {
                                setStar(indexOfChild(v) + 1);
                                if (onRatingChangeListener != null) {
                                    onRatingChangeListener.onRatingChange(indexOfChild(v) + 1);
                                }
                            }

                        }
                    }
            );

            addView(imageView);
        }
    }

    /**
     * @param context
     * @param attrs
     * @return
     */
    private ImageView getStarImageView(Context context, AttributeSet attrs) {
        ImageView imageView = new ImageView(context);
        LinearLayout.LayoutParams para = new LinearLayout.LayoutParams(
                Math.round(starImageSize),
                Math.round(starImageSize)
        );
        para.weight = 1;
        imageView.setLayoutParams(para);
        imageView.setPadding(0, 0, 5, 0);
        imageView.setImageDrawable(starEmptyDrawable);
        imageView.setMaxWidth(10);
        imageView.setMaxHeight(10);
        return imageView;
    }

    /**
     * setting start
     * @param starCount
     */

    public void setStar(float starCount) {

        //浮点数的整数部分
        int fint = (int) starCount;
        BigDecimal b1 = new BigDecimal(Float.toString(starCount));
        BigDecimal b2 = new BigDecimal(Integer.toString(fint));
        //浮点数的小数部分
        float fPoint = b1.subtract(b2).floatValue();


        starCount = fint > this.starCount ? this.starCount : fint;
        starCount = starCount < 0 ? 0 : starCount;

        //drawfullstar
        for (int i = 0; i < starCount; ++i) {
            ((ImageView) getChildAt(i)).setImageDrawable(starFillDrawable);
        }

        //drawhalfstar
        if (fPoint > 0) {
            ((ImageView) getChildAt(fint)).setImageDrawable(starHalfDrawable);

            //drawemptystar
            for (int i = this.starCount - 1; i >= starCount + 1; --i) {
                ((ImageView) getChildAt(i)).setImageDrawable(starEmptyDrawable);
            }
        } else {
            //drawemptystar
            for (int i = this.starCount - 1; i >= starCount; --i) {
                ((ImageView) getChildAt(i)).setImageDrawable(starEmptyDrawable);
            }
        }

        Log.e(MyRatingBar.class.getName(),"starCount"+starCount);
    }

    /**
     * change stat listener
     */
    public interface OnRatingChangeListener {
        void onRatingChange(int RatingCount);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float moveX =event.getX();
                float rX=event.getRawX();
                setStarByEventX((int)moveX);
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    int curHightStarCount=0;
    private void setStarByEventX(int moveX){
        int untilWidth=getWidth()/starCount;
        int hightStartCount=(moveX/untilWidth)+1;
        if(curHightStarCount==hightStartCount){
            return;
        }
        setStar(hightStartCount);
        curHightStarCount=hightStartCount;
    }
}

