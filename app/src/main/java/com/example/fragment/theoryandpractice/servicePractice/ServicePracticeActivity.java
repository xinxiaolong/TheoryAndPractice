package com.example.fragment.theoryandpractice.servicePractice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import com.example.fragment.theoryandpractice.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by xiaolong on 2017/3/17.
 */

public class ServicePracticeActivity extends Activity {


    @InjectView(R.id.btn_startService)
    Button btnStartService;
    @InjectView(R.id.btn_stopService)
    Button btnStopService;
    @InjectView(R.id.btn_bindService)
    Button btnBindService;
    @InjectView(R.id.btn_unBindService)
    Button btnUnBindService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_practice_layout);
        ButterKnife.inject(this);
        initEvent();
    }

    public void initEvent(){
        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService();
            }
        });

        btnStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService();
            }
        });

        btnBindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService();
            }
        });

        btnUnBindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unBindService();
            }
        });
    }

    public void startService(){
        Intent startIntent = new Intent(this, MyService.class);
        startService(startIntent);
    }

    public void stopService(){
        Intent stopIntent = new Intent(this, MyService.class);
        stopService(stopIntent);
    }

    public void bindService(){
        Intent bindIntent = new Intent(this, MyService.class);
        bindService(bindIntent, connection, BIND_AUTO_CREATE);
    }

    public void unBindService(){
        unbindService(connection);
    }

    MyService.MyBinder myBinder;
    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (MyService.MyBinder) service;
            myBinder.startTask();
        }
    };

}
