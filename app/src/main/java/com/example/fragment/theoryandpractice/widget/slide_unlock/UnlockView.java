package com.example.fragment.theoryandpractice.widget.slide_unlock;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.example.fragment.theoryandpractice.R;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.util.Property;
import com.nineoldandroids.view.ViewHelper;

/**
 * 解锁视图
 *
 */
public class UnlockView extends ViewGroup {

    private static final String TAG = "UnlockView";
    /**
     * 是否使用Tint效果
     */
    private boolean mUseTint;

    private ViewDragHelper viewDragHelper;

    private Paint mPaint;

    /**
     * 解锁触发阀值
     * 就是说当用户滚动滑块占当前视图宽度的百分比
     * 比如说 当前 视图宽度 = 1000px
     * 触发比率 = 0.75
     * 那么 触发阀值就是 750 = 1000 * 0.75
     */
    private float unlockTriggerValue = 0.4f;

    /**
     * 动画时长
     */
    private int animationTimeDuration = 300;

    /**
     * ting效果的动画时长
     */
    private int mTintAnimationDuration = 500;

    /**
     * 回弹动画实现
     */
    private ObjectAnimator oa;
    /**
     * 解锁事件触发监听器
     */
    private OnUnlockListener mOnUnlockListener;

    /**
     * 锁视图
     */
    private View mLockView;

    /**
     * 提示视图
     */
    private View mUnlockTip;

    /**
     * 默认的锁和框框的边距
     */
    private int space;

    /**
     * 圆角背景矩形
     */
    private RectF mRoundRect = new RectF();
    /**
     * Tint的值
     */
    private int mExtend;

    /**
     * Tint画笔
     */
    private Paint mTintPaint;
    /**
     * TintColor
     */
    private int mTintColor;
    /**
     * 背景色
     */
    private int mBackgroundColor;

    public UnlockView(Context context) {
        this(context, null);
    }

    public UnlockView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UnlockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.UnlockView);
            this.mUseTint = typedArray.getBoolean(R.styleable.UnlockView_tint, true);
            mTintColor = typedArray.getColor(R.styleable.UnlockView_tintColor, mTintColor);
            mBackgroundColor = typedArray.getColor(R.styleable.UnlockView_backgroundColor, mBackgroundColor);
            space = typedArray.getDimensionPixelOffset(R.styleable.UnlockView_space, space);

            unlockTriggerValue = typedArray.getFloat(R.styleable.UnlockView_trigger, unlockTriggerValue);
            animationTimeDuration = typedArray.getInt(R.styleable.UnlockView_animationTimeDuration, animationTimeDuration);
            mTintAnimationDuration = typedArray.getInt(R.styleable.UnlockView_tintAnimationDuration, mTintAnimationDuration);
            typedArray.recycle();
        }

        mPaint.setColor(mBackgroundColor);
        mTintPaint.setColor(mTintColor);

    }

    /**
     * 初始化
     */
    public void init() {
        this.mTintColor = getContext().getResources().getColor(R.color.green);
        this.mBackgroundColor = getContext().getResources().getColor(R.color.gray);
        space = (int) (getResources().getDisplayMetrics().density * 3);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mTintPaint = new Paint(mPaint);
        viewDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                int minX = space;
                int maxX = getWidth() - space - mLockView.getWidth();

                int width=getWidth();
                int lockViewWidth=mLockView.getWidth();
                int left=child.getLeft();
                int right=child.getRight();


                boolean isEnabled=isEnabled();

                boolean min_x=child.getLeft() > minX;
                boolean max_x=child.getRight() < maxX;
                boolean isSame=child == mLockView;
                return isEnabled() && (child.getLeft() > minX || child.getRight() < maxX) && child == mLockView;
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
                int movedDistance = releasedChild.getRight() - releasedChild.getWidth();
                if (movedDistance >= getWidth() * unlockTriggerValue) {
                    //到终点
                    animToXToPosition(releasedChild, getWidth() - space - mLockView.getWidth(), animationTimeDuration);
                    //如果Tint未开启，开启Tint效果
                    if (!isExpanded()) expandTint(true);
                    //触发解锁时间
                    if (mOnUnlockListener != null) mOnUnlockListener.onUnlock();
                } else {
                    //回到起点
                    animToXToPosition(releasedChild, space, animationTimeDuration);
                    //如果tint效果未关闭，那么关闭Tint
                    if (isExpanded()) collectTint(true);
                }
            }


            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                final int oldLeft = child.getLeft();
                int minX = space;
                int maxX = getWidth() - space - mLockView.getWidth();
                if (left > minX && left < maxX) {
                    child.layout(left, (getHeight() - child.getHeight()) / 2, left + child.getWidth(), (getHeight() - child.getHeight()) / 2 + child.getHeight());
                    computePercent();
                }
                return oldLeft;
            }

            /**
             * {@link ViewDragHelper } 默认实现为 ：
             *
             *   int clampedX = left;
             int clampedY = top;
             final int oldLeft = mCapturedView.getLeft();
             final int oldTop = mCapturedView.getTop();
             *   if (dx != 0) {
             clampedX = mCallback.clampViewPositionHorizontal(mCapturedView, left, dx);
             mCapturedView.offsetLeftAndRight(clampedX - oldLeft);
             }
             if (dy != 0) {
             clampedY = mCallback.clampViewPositionVertical(mCapturedView, top, dy);
             mCapturedView.offsetTopAndBottom(clampedY - oldTop);
             }
             所以这里返回的值会影响到视图的偏移
             但是 又可见{@link View#offsetLeftAndRight(int)} 和 {@link View#offsetLeftAndRight(int)} 的实现代码为
             if (offset != 0) {
             ...
             }
             所以我们如果使得offset的值为0就可以了
             */
            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                return child.getTop();
            }
        });
    }


    /**
     * 计算滑动的百分比
     */
    private void computePercent() {
        int baseX = space + mLockView.getWidth();
        float maxDistance = (getWidth() - baseX) * unlockTriggerValue;
        float currentDistance = mLockView.getRight() - baseX;
        onSlideProgress(currentDistance / maxDistance);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.AT_MOST);
        int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(heightMeasureSpec), MeasureSpec.AT_MOST);

        if (getChildCount() != 2) throw new IllegalArgumentException("Must contained 2 Views.");

        this.mUnlockTip = getChildAt(0);
        this.mLockView = getChildAt(1);

        mLockView.measure(childWidthMeasureSpec, childHeightMeasureSpec);
        int lockHeight = mLockView.getMeasuredHeight();
        int lockWidth = mLockView.getMeasuredWidth();

        mUnlockTip.measure(childWidthMeasureSpec, childHeightMeasureSpec);
        int tipHeight = mUnlockTip.getMeasuredHeight();
        int tipWidth = mUnlockTip.getMeasuredWidth();

        int height = Math.max(tipHeight, lockHeight + space * 2);
        int width;

        switch (MeasureSpec.getMode(widthMeasureSpec)) {
            case MeasureSpec.EXACTLY:
                width = MeasureSpec.getSize(widthMeasureSpec);
                break;
            default:
                width = lockWidth + tipWidth;
                break;
        }
        setMeasuredDimension(width, height);
    }

    /**
     * 使用动画转到指定的位置
     *
     * @param view 需要作用动画的视图
     * @param toX  需要转到的位置
     */
    public void animToXToPosition(final View view, int toX, long animationTime) {
        com.nineoldandroids.util.Property<View, Integer> layoutProperty = new Property<View, Integer>(Integer.class, "layout") {

            @Override
            public void set(View object, Integer value) {
                object.layout(value, (getHeight() - mLockView.getHeight()) / 2,
                        value + object.getWidth(), (getHeight() - mLockView.getHeight()) / 2 + object.getHeight());
                computePercent();
            }

            @Override
            public Integer get(View object) {
                return view.getLeft();
            }
        };

        //原来的动画正在执行
        //取消掉，防止多重动画冲突
        if (oa != null && oa.isRunning()) {
            oa.cancel();
        }
        oa = ObjectAnimator.ofInt(view, layoutProperty, view.getLeft(), toX);
        oa.setInterpolator(new AccelerateInterpolator());
        oa.setDuration(animationTime);
        oa.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return viewDragHelper.shouldInterceptTouchEvent(ev);
    }

    /**
     * 设置解锁事件触发监听器
     *
     * @param listener 需要设置的监听器
     */
    public void setOnUnlockListener(OnUnlockListener listener) {
        this.mOnUnlockListener = listener;
    }

    /**
     * 获取动画时长
     *
     * @return 动画时长
     */
    public long getAnimationTimeDuration() {
        return animationTimeDuration;
    }

    /**
     * 设置动画时长
     *
     * @param animationTimeDuration 需要设置的动画时长
     */
    public void setAnimationTimeDuration(int animationTimeDuration) {
        this.animationTimeDuration = animationTimeDuration;
    }

    /**
     * 获取当前Tint动画的时长
     */
    public long getTintAnimationDuration() {
        return mTintAnimationDuration;
    }

    /**
     * 设置Tint动画的时长
     */
    public void setTintAnimationDuration(int tintAnimationDuration) {
        this.mTintAnimationDuration = tintAnimationDuration;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mExtend = w;
    }

    /**
     * 重置这个解锁视图的状态
     * 怎么讲呢，就是说恢复到未解锁时的状态
     * 这个方法应该在视图可以获取到的大小时调用
     * 不然可能导致视图不可见
     *
     * @param hasAnim 是否使用动画
     */
    public void resetState(boolean hasAnim) {
        if (mLockView != null) {
            animToXToPosition(mLockView, space, hasAnim ? animationTimeDuration : 0);
            collectTint(hasAnim);
            if (mOnUnlockListener != null) mOnUnlockListener.onLock();
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        mRoundRect.left = 0;
        mRoundRect.top = 0;
        mRoundRect.bottom = getHeight();
        mRoundRect.right = getWidth();

        //计算圆角
        float round = (Math.min(mLockView.getWidth(), mLockView.getHeight()) + space) / 2;

        //绘制背景
        canvas.drawRoundRect(mRoundRect, round, round, mPaint);

        if (mUseTint){
            //高度变化，在接近结束时需要将高度变小以此来适应圆角
            mRoundRect.left = mExtend;
            if (mRoundRect.left >= getWidth() - mLockView.getWidth()) {
                mRoundRect.top = mRoundRect.top + (mRoundRect.left - (getWidth() - mLockView.getWidth())) / 2;
                mRoundRect.bottom = mRoundRect.bottom - (mRoundRect.left - (getWidth() - mLockView.getWidth())) / 2;
            }

            //绘制绿色的Tint效果
            canvas.drawRoundRect(mRoundRect, round, round, mTintPaint);
        }
        super.dispatchDraw(canvas);
    }

    @Override
    public void addView(View child, int index, LayoutParams params) {
        if (getChildCount() == 2)
            throw new IllegalArgumentException("Can't contain more child view.");
        super.addView(child, index, params);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int viewWidth = r - l;
        int viewHeight = b - t;

        int lockViewWidth = mLockView.getMeasuredWidth();
        int lockViewHeight = mLockView.getMeasuredHeight();

        mLockView.layout(space, (viewHeight - lockViewHeight) / 2, lockViewWidth + space, (viewHeight - lockViewHeight) / 2 + lockViewHeight);

        int tipViewMinLeft = lockViewWidth + space;
        int tipWidth = mUnlockTip.getMeasuredWidth();
        int tipHeight = mUnlockTip.getMeasuredHeight();

        int tipX = ((viewWidth + tipViewMinLeft) - tipWidth) / 2;
        mUnlockTip.layout(tipX, (viewHeight - tipHeight) / 2, tipX + tipWidth, (viewHeight - tipHeight) / 2 + tipHeight);
    }

    /**
     * 当滑动进度更新后触发
     *
     * @param percent 百分比
     */
    public void onSlideProgress(float percent) {
        //设置提示视图的透明度
        ViewHelper.setAlpha(mUnlockTip, 1 - percent);
    }

    /**
     * Tint是否是处于展开状态
     *
     * @return true 或 false
     */
    public boolean isExpanded() {
        return mExtend == 0;
    }

    /**
     * 展开Tint效果
     *
     * @param hasAnim 是否使用动画
     */
    public void expandTint(boolean hasAnim) {
        if (!hasAnim || !mUseTint) {
            setExpand(0);
            if (mOnUnlockListener != null) mOnUnlockListener.onTintExpanded();
            return;
        }

        ObjectAnimator ao = ObjectAnimator.ofInt(this, "Expand", getWidth() - (getWidth() - mLockView.getRight()) - mLockView.getWidth(), 0);
        ao.setDuration(mTintAnimationDuration);
        ao.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                if (mOnUnlockListener != null) mOnUnlockListener.onTintExpanding(fraction);
            }
        });

        ao.setInterpolator(new DecelerateInterpolator());
        ao.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (mOnUnlockListener != null) mOnUnlockListener.onTintExpanded();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                setExpand(0);
                if (mOnUnlockListener != null) mOnUnlockListener.onTintExpanded();
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        ao.start();
    }

    /**
     * 关闭Tint
     *
     * @param hasAnim 是否使用动画
     */
    public void collectTint(boolean hasAnim) {
        if (!hasAnim || !mUseTint) {
            setExpand(getWidth() - mLockView.getWidth() - space);
            if (mOnUnlockListener != null) mOnUnlockListener.onTintCollected();
            return;
        }

        ObjectAnimator ao = ObjectAnimator.ofInt(this, "Expand", 0, getWidth());
        ao.setDuration(animationTimeDuration);
        ao.setInterpolator(new AccelerateInterpolator());
        ao.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                if (mOnUnlockListener != null) mOnUnlockListener.onTintCollecting(fraction);
            }
        });
        ao.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (mOnUnlockListener != null) mOnUnlockListener.onTintCollected();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                setExpand(getWidth() - mLockView.getWidth() - space);
                if (mOnUnlockListener != null) mOnUnlockListener.onTintCollected();
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        ao.start();
    }

    /**
     * 获取当前的Tint的值
     */
    public int getExpand() {
        return mExtend;
    }

    /**
     * 设置当前Tint的值
     */
    public void setExpand(int extend) {
        this.mExtend = extend;
        postInvalidate();
    }

    /**
     * 当前是否处于锁住
     * 这个判断是否是处于锁住 '状态'
     * 用 '状态' 描述并不准确，可以说是是否处于锁定范围
     */
    public boolean isLocked() {
        int movedDistance = mLockView.getRight() - mLockView.getWidth();
        return movedDistance < getWidth() * unlockTriggerValue;
    }

    /**
     * 获取当前的Tint颜色
     *
     * @return 当前的Tint颜色
     */
    public int getTintColor() {
        return this.mTintColor;
    }

    /**
     * 设置Tint颜色
     *
     * @param tintColor 需要设置的Tint颜色
     */
    public void setTintColor(int tintColor) {
        this.mTintColor = tintColor;
        mTintPaint.setColor(mTintColor);
        postInvalidate();
    }

    /**
     * 解锁监听器
     */
    public interface OnUnlockListener {
        /**
         * 当解锁被触发后执行
         */
        void onUnlock();

        /**
         * 当Tint展开后执行
         */
        void onTintExpanded();

        /**
         * 当Tint正在展开时执行
         *
         * @param percent 展开的进度 0 - 1
         */
        void onTintExpanding(float percent);


        /**
         * 当Tint正在关闭时执行
         *
         * @param percent 关闭的进度 0 - 1
         */
        void onTintCollecting(float percent);

        /**
         * 当Tint被关闭后执行
         */
        void onTintCollected();

        /**
         * 当锁定时触发
         */
        void onLock();
    }


}
