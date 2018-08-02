package qktx.xinxiaolong.com.timetask;

import android.content.Context;
import android.os.Build;

import qktx.xinxiaolong.com.timetask.alarm.AlarmJobManager;
import qktx.xinxiaolong.com.timetask.jobservice.DaemonAlarmJobManager;

/**
 * Created by xiaolong on 2018/8/2.
 * email：xinxiaolong123@foxmail.com
 */

public class TimerServiceManager {

    private Context mContext;
    public static volatile TimerServiceManager timerService;
    private AlarmJobManager alarmJobManager;
    private DaemonAlarmJobManager jobSchedulerManager;
    private TimerServiceManager() {

    }

    private TimerServiceManager init(Context context) {
        mContext = context;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            alarmJobManager=AlarmJobManager.get(mContext);
        }else {
            alarmJobManager=AlarmJobManager.get(mContext);
            jobSchedulerManager=DaemonAlarmJobManager.get(mContext);
        }
        return this;
    }

    public static TimerServiceManager get(Context context) {
        if (timerService == null) {
            timerService = new TimerServiceManager();
        }
        return timerService.init(context);
    }


    public void startTimerService(long delayTime) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
             //5.0版本及以下
            initAlarmJobManager(delayTime);
            alarmJobManager.startAlarm();
        } else {
            //5.0版本及以上
            initAlarmJobManager(delayTime);
            alarmJobManager.startAlarm();
            jobSchedulerManager.startDataSyncJobScheduler(1);

        }
    }

    private AlarmJobManager initAlarmJobManager(long delayTime){
        alarmJobManager.setAlarmId(1);
        alarmJobManager.setDelayTime(delayTime);
        alarmJobManager.cancelAlarm();
        return alarmJobManager;
    }


    public void stopTimeService() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            alarmJobManager.cancelAlarm();
        }else{
            jobSchedulerManager.stopAllDataSyncJobScheduler();
            alarmJobManager.cancelAlarm();
        }

    }
}
