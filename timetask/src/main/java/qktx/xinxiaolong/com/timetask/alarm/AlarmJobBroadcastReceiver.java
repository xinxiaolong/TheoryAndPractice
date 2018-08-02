package qktx.xinxiaolong.com.timetask.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by xiaolong on 2018/8/2.
 * email：xinxiaolong123@foxmail.com
 */

public class AlarmJobBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, AlarmService.class);
        context.startService(i);
    }
}
