package com.example.fragment.theoryandpractice.notificationPractice;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import com.example.fragment.theoryandpractice.R;

import java.util.Random;

/**
 * Created by xiaolong on 16/7/20.
 */
public class ProgressNotificationHandler {

    private static int progresID;
    private static ProgressNotificationHandler nHandler;
    private static NotificationManager mNotificationManager;
    private NotificationCompat.Builder nBuilder;


    public static ProgressNotificationHandler getInstance(Context context) {
        if (nHandler == null) {
            progresID = new Random().nextInt(1000);
            nHandler = new ProgressNotificationHandler();
            mNotificationManager =
                    (NotificationManager) context.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return nHandler;
    }


    public void createProgressNotification(final Context context) {
        progresID= new Random().nextInt(1000);
        nBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_upload)
                .setContentTitle("正在后台上传图片")
                .setContentText("请勿急躁")
                .setTicker("正在后台上传图片...")
                .setUsesChronometer(true)
                .setProgress(100, 0, true);
    }

    public void startUpload() {
        mNotificationManager.notify(progresID, nBuilder.build());
    }


    public void onProgress(int max, int current) {
        int currentProgress = (100 / max) * current;
        nBuilder
                .setContentTitle("上传中...")
                .setContentText("当前进度" + current + "/" + max)
                .setProgress(100, currentProgress, false)
                .setSmallIcon(R.drawable.ic_upload)
                .setContentInfo(currentProgress + " %");
        mNotificationManager.notify(progresID, nBuilder.build());
    }


    public void uploadComplete() {
        nBuilder.setContentText("照片已经上传完成了")
                .setContentTitle("照片已经上传完成!")
                .setTicker("照片已经上传完成!")
                .setSmallIcon(R.drawable.ic_upload_complete)
                .setUsesChronometer(false);
        mNotificationManager.notify(progresID, nBuilder.build());
    }
}
