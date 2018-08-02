package qktx.xinxiaolong.com.timetask.alarm;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by xiaolong on 2018/8/1.
 * email：xinxiaolong123@foxmail.com
 */

public class AlarmService extends Service{

    public static String TAG="TIME_TASK";
    AlarmJobManager alarmJobManager;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        alarmJobManager=AlarmJobManager.get(this);
        Log.e(TAG,"AlarmService:  onCreate()");
        EventBus.getDefault().post("AlarmService:  onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.e(TAG,"AlarmService:onStartCommand()   startId="+startId);
        alarmJobManager.startAlarm();
        EventBus.getDefault().post("AlarmService:  onStartCommand()   startId="+startId);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {

        Log.e(TAG,"onDestroy()");
        EventBus.getDefault().post("AlarmService:    onDestroy()");
        super.onDestroy();
    }
}
