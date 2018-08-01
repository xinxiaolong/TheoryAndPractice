package qktx.xinxiaolong.com.timetask.alarm;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by xiaolong on 2018/8/1.
 * email：xinxiaolong123@foxmail.com
 */

public class AlarmService extends Service{


    public static String TAG="TIME_TASK";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(TAG,"onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v(TAG,"onStartCommand()   startId="+startId);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {

        Log.v(TAG,"onDestroy()");
        super.onDestroy();
    }
}
