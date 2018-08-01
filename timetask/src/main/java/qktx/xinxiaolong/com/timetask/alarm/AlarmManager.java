package qktx.xinxiaolong.com.timetask.alarm;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by xiaolong on 2018/8/1.
 * email：xinxiaolong123@foxmail.com
 */

public class AlarmManager {

    Context mContext;
    public AlarmManager(Context context){
        mContext=context;
    }

    public void startAlarm(){
        @SuppressLint("ServiceCast")
        AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(ALARM_SERVICE);


    }
}
