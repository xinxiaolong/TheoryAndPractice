package qktx.xinxiaolong.com.timetask.jobservice;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import qktx.xinxiaolong.com.timetask.alarm.AlarmJobManager;

/**
 * Created by xiaolong on 2018/8/1.
 * email：xinxiaolong123@foxmail.com
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class DaemonAlarmJobService extends JobService {

    private static String TAG="TIME_TASK";
    private AlarmJobManager alarmJobManager;


    @Override
    public void onCreate() {
        super.onCreate();
        alarmJobManager=AlarmJobManager.get(this);
        Log.d(TAG,"DaemonAlarmJobService：   onCreate()");
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        if(alarmJobManager.isStarted()){
            Log.e(TAG,"DaemonAlarmJobService：   闹钟已开启");
            EventBus.getDefault().post("DataSyncJobService：   闹钟已开启");
        }else {
            Log.e(TAG,"DaemonAlarmJobService：   闹钟未开启，尝试打开");
            EventBus.getDefault().post("DataSyncJobService：   闹钟未开启，尝试打开");
            alarmJobManager.setAlarmId(System.currentTimeMillis());
            alarmJobManager.startAlarm();
        }
        return false;
    }


    @Override
    public boolean onStopJob(JobParameters params) {
        Log.v(TAG,"DaemonAlarmJobService ：  onStopJob()");
        EventBus.getDefault().post("DaemonAlarmJobService:  onStopJob()");
        alarmJobManager.cancelAlarm();
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"DaemonAlarmJobService：   onDestroy()");

    }
}
