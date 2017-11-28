package com.example.fragment.theoryandpractice.threadPractice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by xiaolong on 16/7/19.
 */
public class TestService extends Service{


    @Override
    public void onCreate() {
        Log.e("DemoLog","TestService -> onCreate, Thread ID: " + Thread.currentThread().getId());
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("DemoLog", "TestService -> onStartCommand, startId: " + startId + ", Thread ID: " + Thread.currentThread().getId());
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("DemoLog", "TestService -> onBind, Thread ID: " + Thread.currentThread().getId());
        return null;
    }

    @Override
    public void onDestroy() {
        Log.e("DemoLog", "TestService -> onDestroy, Thread ID: " + Thread.currentThread().getId());
        super.onDestroy();
    }
}
