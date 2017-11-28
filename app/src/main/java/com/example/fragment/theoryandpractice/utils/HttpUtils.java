package com.example.fragment.theoryandpractice.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * Created by xiaolong.
 * on 2016-01-14 下午12:20.
 */
public class HttpUtils {

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if(info == null){
                return false;
            }else{
                if(info.isAvailable()){
                    return true;
                }
            }
        }
        return false;
    }

    public static final int NET_TYPE_NO=0; //无网络
    public static final int NET_TYPE_WIFI=1; //wifi
    public static final int NET_TYPE_M_2G =2;  //2G
    public static final int NET_TYPE_M_3G =3;  //3G
    public static final int NET_TYPE_M_4G =4;  //4G
    public static final int NET_TYPE_UNKNOWN=5; //未知网络类型

    public static int getNetType(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null || !info.isAvailable()) {
            return NET_TYPE_NO;
        }
        if (info.getType() == ConnectivityManager.TYPE_WIFI) {
            return NET_TYPE_WIFI;
        }
        if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
            int sub = info.getSubtype();
            switch (sub) {
                case TelephonyManager.NETWORK_TYPE_GPRS:
                case TelephonyManager.NETWORK_TYPE_EDGE:
                case TelephonyManager.NETWORK_TYPE_CDMA://电信的2G
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                case TelephonyManager.NETWORK_TYPE_IDEN:
                    //以上的都是2G网络
                    return NET_TYPE_M_2G;

                case TelephonyManager.NETWORK_TYPE_UMTS:
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                case TelephonyManager.NETWORK_TYPE_HSPA:
                case TelephonyManager.NETWORK_TYPE_EVDO_B:
                case TelephonyManager.NETWORK_TYPE_EHRPD:
                case TelephonyManager.NETWORK_TYPE_HSPAP:
                    //以上的都是3G网络
                    return NET_TYPE_M_3G;
                case TelephonyManager.NETWORK_TYPE_LTE:
                    return NET_TYPE_M_4G;
                case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                    return NET_TYPE_UNKNOWN;
                default:
                    return NET_TYPE_UNKNOWN;
            }
        }
        return NET_TYPE_UNKNOWN;
    }
}
