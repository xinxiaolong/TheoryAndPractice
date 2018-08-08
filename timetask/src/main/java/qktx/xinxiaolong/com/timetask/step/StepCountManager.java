package qktx.xinxiaolong.com.timetask.step;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventCallback;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.annotation.RequiresApi;


import static android.content.Context.SENSOR_SERVICE;

/**
 * Created by xiaolong on 2018/8/3.
 * email：xinxiaolong123@foxmail.com
 */

public class StepCountManager {

    private Context mContext;
    private SharedPreferences sharedPreferences;
    private SensorManager sensorManager;
    private AcquireStepCallBack mCallBack;
    private SensorEventCallback sensorEventCallback;

    public StepCountManager(Context context){
        mContext=context.getApplicationContext();
        sharedPreferences = context.getSharedPreferences("qktx", Context.MODE_PRIVATE);
    }

    public static void evokeStepCountStrategy(Context context){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            //7.0不用开启计步的服务
        }else {
            Intent intent = new Intent(context, StepService.class);
            context.startService(intent);
        }
    }

    /**
     * 获取步数方法
     * 1：版本为7.0及以上：通过传感器拿到当前的步数。一次性及时拿到，不用开后台服务。
     * 2：版本为7.0以下：要从缓存文件中拿，缓存文件的计步数据是后台计步服务实时写入，依赖后台计步服务。
     * @param callBack
     */
    public void getStepCount(AcquireStepCallBack callBack){
        mCallBack=callBack;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            acquireByCounterSensor();
        } else {
            int stepCount = sharedPreferences.getInt("recordTotalCount", 0);
            int lastRecordCount = sharedPreferences.getInt("lastRecordCount", 0);
            int thisCount=stepCount-lastRecordCount;

            sharedPreferences.edit().putInt("lastRecordCount", stepCount).commit();
            callBack.callBack(thisCount);
        }
    }

    /**
     * 7.0系统可以通过TYPE_STEP_COUNTER这个传感器实时获取步数
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void acquireByCounterSensor() {
        sensorManager = (SensorManager) mContext
                .getSystemService(SENSOR_SERVICE);
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        sensorEventCallback=new SensorEventCallback() {
            @Override
            public void onSensorChanged(SensorEvent event) {

                int lastRecordCount = sharedPreferences.getInt("lastRecordCount", 0);
                int totalCount = (int) (event.values[0]);
                //这里做了下取舍:如果之前从没存过记步数，则上传0。
                int thisCount = lastRecordCount==0?0:totalCount-lastRecordCount;
                sharedPreferences.edit().putInt("lastRecordCount", totalCount).commit();
                mCallBack.callBack(thisCount);
            }
        };

        sensorManager.registerListener(sensorEventCallback, countSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    /**
     * 注销对象
     */
    public void destroy(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            sensorManager.unregisterListener(sensorEventCallback);
        }
        mCallBack=null;
    }

}
