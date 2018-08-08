package qktx.xinxiaolong.com.timetask.alarm;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import qktx.xinxiaolong.com.timetask.manager.UploadBehaviorDataExecute;
import qktx.xinxiaolong.com.timetask.step.StepCountManager;

/**
 * Created by xiaolong on 2018/8/1.
 * email：xinxiaolong123@foxmail.com
 */

public class AlarmService extends Service{

    private static String TAG="TIME_TASK";
    private AlarmJobManager alarmJobManager;
    private UploadBehaviorDataExecute uploadExecute;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        alarmJobManager=AlarmJobManager.get(this);
        uploadExecute=new UploadBehaviorDataExecute(this);

        //唤醒计步服务
        StepCountManager.evokeStepCountStrategy(this);
        //启动定时日志上传功能
        uploadExecute.startUp();

        Log.e(TAG,"AlarmService:  onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG,"AlarmService:onStartCommand()   startId="+startId);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.e(TAG,"destroy()");
        alarmJobManager.startAlarm();
        super.onDestroy();
    }

}
