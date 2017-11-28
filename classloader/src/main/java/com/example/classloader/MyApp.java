package com.example.classloader;

import android.app.Application;
import android.content.Context;

import com.example.multidex.FixDexUtils;

public class MyApp extends Application {
	
	@Override
	public void onCreate() {
		super.onCreate();
	}
	
	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		FixDexUtils.loadFixDex(base);
	}

}
