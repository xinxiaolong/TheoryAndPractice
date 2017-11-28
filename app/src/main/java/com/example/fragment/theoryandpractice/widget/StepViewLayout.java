package com.example.fragment.theoryandpractice.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.fragment.theoryandpractice.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaolong on 16/6/22.
 */
public class StepViewLayout extends LinearLayout {

    Context mContext;
    public StepViewLayout(Context context) {
        super(context);
        init(context);
    }

    public StepViewLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    private void init(Context context) {
        mContext = context;
        DrawView drawView=new DrawView(context);
        addView(drawView,getWeightParams());
    }

    private LinearLayout.LayoutParams getWeightParams(){
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        return params;
    }


    class DrawView extends View{
        Context mContext;
        Bitmap bitmapPoint;
        Bitmap pointComplete;
        List<Step> steps;
        List<Line> lines = new ArrayList<>();
        List<Point> points = new ArrayList<>();
        List<Text> texts = new ArrayList<>();
        float lineY;
        float pointWidth;
        float lineWidth;
        int drawablePadding = 40;

        public DrawView(Context context) {
            super(context);
            init(context);
        }

        public DrawView(Context context, AttributeSet attrs) {
            super(context, attrs);
            init(context);
        }

        Handler handler = new Handler();

        private void init(Context context) {
            mContext = context;
            pointComplete = BitmapFactory.decodeResource(getResources(), R.drawable.step_point_complete);
            bitmapPoint = BitmapFactory.decodeResource(getResources(), R.drawable.step_point);
            pointWidth = bitmapPoint.getWidth();
            lineY = pointWidth / 2;
            steps = getSteps();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    calculateCoordinate(steps);
                }
            }, 1000);

        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            drawPoints(canvas);
            drawLines(canvas);
            drawText(canvas);
        }


        private void drawPoints(Canvas canvas) {
            for (int i = 0; i < points.size(); i++) {
                drawPoint(canvas,i);
            }
        }
        private void drawPoint(Canvas canvas,int i){
            Point point = points.get(i);
            Bitmap bitmap = point.compelete ? pointComplete : bitmapPoint;
            canvas.drawBitmap(bitmap, point.startX, 0, null);
        }

        private void drawLines(Canvas canvas) {
            Paint paint = new Paint();
            // 绘制矩形区域-实心矩形
            paint.setAntiAlias(true);
            // 设置样式-填充
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            paint.setStrokeWidth(0.5f);
            for (int i = 0; i < lines.size(); i++) {
                Line line = lines.get(i);
                paint.setColor(line.color);
                canvas.drawLine(line.startX, lineY, line.stopX, lineY, paint);
            }
        }

        private void drawText(Canvas canvas) {

            TextPaint paint = new TextPaint();
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            canvas.translate(0, mContext.getResources().getDimensionPixelOffset(R.dimen.step_point_text_distance));

            float currentTranslateX = 0;
            for (int i = 0; i < texts.size(); i++) {
                Text text = texts.get(i);
                paint.setColor(text.color);
                paint.setTextSize(mContext.getResources().getDimensionPixelSize(R.dimen.text_size_f11));

                float textWidth = paint.measureText(text.name);
                textWidth = textWidth / 2; //文字两行显示 所以实际宽度要除2
                float startX = (text.pointStartX - (textWidth / 2)) + pointWidth / 2;
                float translateX = startX - currentTranslateX;//计算出较上次的X轴需要偏移位置

                int newLineWidth=(int)Math.ceil(textWidth);
                StaticLayout layout = new StaticLayout(text.name, paint,newLineWidth, Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);

                canvas.translate(translateX, 0);
                layout.draw(canvas);
                currentTranslateX = startX;

            }
        }


        /**
         * 获取自身的宽度
         *
         * @return
         */
        private int getRealWidth() {
            return StepViewLayout.this.getMeasuredWidth();
        }

        /**
         * 画图的范围
         * 起始坐标
         *
         * @return
         */
        private Rect getStepDrawbleRange() {
            Rect rect = new Rect();
            rect.left = drawablePadding;
            rect.right = getRealWidth() - drawablePadding;
            return rect;
        }

        class Line {
            int color;
            float startX;
            float stopX;
        }

        class Point {
            boolean compelete;
            float startX;
        }

        class Step {
            String name;
            boolean complete;
        }

        class Text {
            float pointStartX;
            int color;
            String name;
        }

        private List<Step> getSteps() {
            List<Step> steps = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                Step step = new Step();
                step.name = "提交订单";
                if (i >= 2) {
                    step.complete = false;
                } else {
                    step.complete = true;
                }
                steps.add(step);
            }
            return steps;
        }

        private void calculateCoordinate(List<Step> steps) {
            Rect rect = getStepDrawbleRange();
            int lineCount = steps.size() - 1;
            int totalWidth = rect.right - rect.left;
            float totalPointWidth = steps.size() * pointWidth;
            lineWidth = (totalWidth - totalPointWidth) / lineCount;

            float currentX = rect.left;
            for (int i = 0; i < steps.size(); i++) {
                Step step = steps.get(i);
                //添加点位置信息列表
                Point point = addPointList(step, currentX);
                //添加线的位置信息
                Step nextStep=i<(steps.size() - 1)?steps.get(i + 1):null;
                fillTextList(step,nextStep, point.startX);

                if (i < steps.size() - 1) {
                    //添加线的位置信息
                    Line line = fillLineList(steps.get(i + 1), currentX);
                    currentX = line.stopX;
                }

            }
            invalidate();
        }

        private Point addPointList(Step step, float currentX) {
            Point point = new Point();
            point.startX = currentX;
            point.compelete = step.complete;
            points.add(point);
            return point;
        }

        private Line fillLineList(Step nextStep, float currentX) {
            Line line = new Line();
            line.startX = currentX + pointWidth;
            line.stopX = line.startX + lineWidth;
            line.color = nextStep.complete ? Color.RED : Color.GRAY;
            lines.add(line);
            return line;
        }

        private void fillTextList(Step step,Step nextStep, float pointStartX) {
            Text text = new Text();
            text.pointStartX = pointStartX;
            text.color = step.complete&&nextStep!=null&&!nextStep.complete ? Color.RED : Color.GRAY;
            text.name = step.name;
            texts.add(text);
        }
    }
}
