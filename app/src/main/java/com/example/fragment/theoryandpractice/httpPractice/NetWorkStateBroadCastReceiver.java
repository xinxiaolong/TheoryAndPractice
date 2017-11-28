package com.example.fragment.theoryandpractice.httpPractice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.fragment.theoryandpractice.utils.HttpUtils;

/**
 * Created by xiaolong.
 * on 2016-01-14 上午11:50.
 */
public class NetWorkStateBroadCastReceiver extends BroadcastReceiver {

    public static final String ACTION = "android.net.conn.CONNECTIVITY_CHANGE";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (ACTION.equals(intent.getAction())) {
            String netTypeText = "";
            int netType = HttpUtils.getNetType(context);
            switch (netType) {
                case HttpUtils.NET_TYPE_NO:
                    netTypeText = "无网络";
                    break;
                case HttpUtils.NET_TYPE_WIFI:
                    netTypeText = "WIFI网络";
                    break;
                case HttpUtils.NET_TYPE_M_2G:
                    netTypeText = "2G网络";
                    break;
                case HttpUtils.NET_TYPE_M_3G:
                    netTypeText = "3G网络";
                    break;
                case HttpUtils.NET_TYPE_M_4G:
                    netTypeText = "4G网络";
                    break;
                default:
                    netTypeText = "手机移动网络";
                    break;
            }
            Log.e(NetWorkStateBroadCastReceiver.class.getName(), netTypeText);
        }
    }
}
