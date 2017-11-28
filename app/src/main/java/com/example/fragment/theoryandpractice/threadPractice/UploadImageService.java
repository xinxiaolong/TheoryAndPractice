package com.example.fragment.theoryandpractice.threadPractice;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fragment.theoryandpractice.R;
import com.example.fragment.theoryandpractice.notificationPractice.ProgressNotificationHandler;
import com.example.fragment.theoryandpractice.notificationPractice.TestActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaolong on 16/7/19.
 */
public class UploadImageService extends Service {


    private static final String TAG = UploadImageService.class.getName();
    private ProgressNotificationHandler notificationHandler;
    private List<String> imageUrlList = new ArrayList<>();
    private int maxImageCount;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        notificationHandler = ProgressNotificationHandler.getInstance(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        notificationHandler.createProgressNotification(this);
        imageUrlList = intent.getStringArrayListExtra("imageUrlList");
        maxImageCount = imageUrlList.size();
        createWindowManagerView();
        startUploadImageList();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    interface OnUploadListener {
        void onComplete(String url);

        void onFail(String url);
    }


    private void startUploadImageList() {
        startNotification();
        startUploadImageLoop();
    }


    private void startUploadImageLoop() {
        if (imageUrlList.size() == 0) {
            onCompleteNotification();
        } else {
            onProgressNotification();
            uploadImage(imageUrlList.get(0), new OnUploadListener() {
                @Override
                public void onComplete(String url) {
                    imageUrlList.remove(url);
                    startUploadImageLoop();
                }

                @Override
                public void onFail(String url) {

                }
            });
        }
    }


    private void uploadImage(final String imageUrl, final OnUploadListener listener) {
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                listener.onComplete(imageUrl);
            }
        }.start();
    }


    private void startNotification() {
        notificationHandler.startUpload();
    }

    private void onProgressNotification() {
        notificationHandler.onProgress(maxImageCount, (maxImageCount - imageUrlList.size()));
    }

    private void onCompleteNotification() {
        notificationHandler.uploadComplete();
    }


    private void createWindowManagerView() {
        WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();
        final WindowManager mWindowManager = (WindowManager) getApplication().getSystemService(getApplication().WINDOW_SERVICE);
        //设置window type
        wmParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        //背景设置透明，不然会出现黑边框
        wmParams.format = PixelFormat.TRANSLUCENT;
        //调整悬浮窗显示的停靠位置屏幕中间
        wmParams.gravity = Gravity.CENTER;
        //设置悬浮窗口长宽数据
        wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        final LayoutInflater inflater = LayoutInflater.from(getApplication());
        //定义浮动窗口布局
        final LinearLayout mFloatLayout = (LinearLayout) inflater.inflate(R.layout.include_float_layout, null);
        //添加mFloatLayout
        mWindowManager.addView(mFloatLayout, wmParams);
        //浮动窗口按钮
        TextView cancel = (TextView) mFloatLayout.findViewById(R.id.cancel);
        TextView submit = (TextView) mFloatLayout.findViewById(R.id.submit);

        mFloatLayout.measure(View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED), View.MeasureSpec
                .makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWindowManager.removeView(mFloatLayout);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWindowManager.removeView(mFloatLayout);
                Intent intent=new Intent();
                intent.setClass(UploadImageService.this, TestActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }


}
