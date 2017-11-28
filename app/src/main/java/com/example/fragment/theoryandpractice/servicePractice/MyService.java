package com.example.fragment.theoryandpractice.servicePractice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by xiaolong on 2017/3/17.
 */

public class MyService extends Service {

    public static final String TAG = MyService.class.getName();
    boolean loop=false;
    MyBinder myBinder=new MyBinder();
    int startId;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG,"service onCreate()");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.startId=startId;
        Log.e(TAG,"service onStartCommand() flags="+flags+"   startId"+startId+"  loop="+loop);
        loop=true;
        new Thread(runnable).start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                stopSelf();
            }
        },7000);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.e(TAG,"service onDestroy()");
        loop=false;
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG,"service onUnbind()");
        return super.onUnbind(intent);
    }


    class MyBinder extends Binder {
        public void startTask(){
            loop=true;
            new Thread(runnable).start();
        }
    }


    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            while (loop){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.e(TAG,"service startTask() startId"+startId);
            }
        }
    };
}
