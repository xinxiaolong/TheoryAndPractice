package com.example.fragment.theoryandpractice;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.squareup.leakcanary.LeakCanary;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * Created by xiaolong.
 * on 2016-01-14 下午12:23.
 *
 * zxing https://github.com/yipianfengye/android-zxingLibrary
 */
public class PracticeApplication extends Application{


    private static Context context;

    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
        ImageLoaderConfiguration configuration=ImageLoaderConfiguration.createDefault(context);
        ImageLoader.getInstance().init(configuration);
        ZXingLibrary.initDisplayOpinion(this);
        LeakCanary.install(this);
    }

    public static Context getContext() {
        return context;
    }
}
