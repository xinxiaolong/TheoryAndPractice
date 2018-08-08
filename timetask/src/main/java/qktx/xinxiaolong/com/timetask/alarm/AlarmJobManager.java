package qktx.xinxiaolong.com.timetask.alarm;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.SystemClock;
import android.util.Log;

import java.io.Serializable;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by xiaolong on 2018/8/1.
 * email：xinxiaolong123@foxmail.com
 */

public class AlarmJobManager implements Serializable{

    public static String TAG="TIME_TASK";


    private Context mContext;
    private static AlarmJobManager alarmJobManager;
    //默认闹钟执行时间，测试时写为10秒
    private long delayTime=1000*10;
    private long alarmId=System.currentTimeMillis();

    private AlarmJobManager() {

    }

    private AlarmJobManager init(Context context){
        mContext=context;;
        return this;
    }


    public static AlarmJobManager get(Context context){
        if(alarmJobManager==null){
            alarmJobManager=new AlarmJobManager();
        }
        return alarmJobManager.init(context.getApplicationContext());
    }

    public void setAlarmId(long alarmId){
        this.alarmId=alarmId;
    }

    public void setDelayTime(long delayTime){
        this.delayTime=delayTime;
    }

    public void startAlarm() {
        if(mContext==null){
            return;
        }
        long triggerAtTime = delayTime;

        AlarmManager am = (AlarmManager) mContext.getSystemService(ALARM_SERVICE);
        PendingIntent pi = PendingIntent.getBroadcast(mContext, 0, getIntent(), PendingIntent.FLAG_CANCEL_CURRENT);
        am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime()+triggerAtTime, pi);

        Log.v(TAG,"AlarmJobManager:   启动了闹钟");
    }

    public void cancelAlarm(){

        AlarmManager am = (AlarmManager) mContext.getSystemService(ALARM_SERVICE);
        PendingIntent pi = PendingIntent.getBroadcast(mContext, 0, getIntent(), PendingIntent.FLAG_NO_CREATE);

        if(pi==null){
            Log.v(TAG,"尝试取消的闹钟找不到");
        }else {
            am.cancel(pi);
            Log.v(TAG,"尝试取消闹钟");
        }
    }

    /**
     * 判断闹钟是否已经运行
     * @return
     */
    public boolean isStarted(){
        PendingIntent pendingIntent=PendingIntent.getBroadcast(mContext, 0,getIntent(), PendingIntent.FLAG_NO_CREATE);
        boolean alarmUp= pendingIntent!= null;
        return alarmUp;
    }


    private Intent getIntent(){
        Intent intent = new Intent(mContext, AlarmJobBroadcastReceiver.class);
        intent.setData(Uri.parse("content://calendar/calendar_alerts/"+alarmId));
        intent.setAction(AlarmJobBroadcastReceiver.class.getSimpleName());
        return intent;
    }

}
