package qktx.xinxiaolong.com.timetask.manager;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import qktx.xinxiaolong.com.timetask.alarm.AlarmService;
import qktx.xinxiaolong.com.timetask.step.AcquireStepCallBack;
import qktx.xinxiaolong.com.timetask.step.StepCountManager;


/**
 * Created by xiaolong on 2018/8/3.
 * email：xinxiaolong123@foxmail.com
 *
 * 日志上传执行者：
 * 1：先逐步获取相关数据。
 * 2：执行数据的上传。
 * 3：完成后清理对象。
 */

public class UploadBehaviorDataExecute {

    private static String TAG = "TIME_TASK";

    private Context mContext;
    private int mStepCount;
    private StepCountManager stepCountManager;


    public UploadBehaviorDataExecute(Context context) {
        mContext = context.getApplicationContext();
        stepCountManager=new StepCountManager(mContext);
    }

    /**
     * 启动日志上传
     */
    public void startUp() {
        Log.e(TAG, "启动日志上传");
        acquireLocationData();
    }

    /**
     * 获取定位数据
     */
    private void acquireLocationData() {
        Log.e(TAG, "获取定位数据");
        acquireStepCount();
    }

    /**
     * 获取步数数据
     */
    private void acquireStepCount() {
        Log.e(TAG, "获取用户步数数据");
        stepCountManager.getStepCount(new AcquireStepCallBack() {
            @Override
            public void callBack(int stepCount) {
                mStepCount=stepCount;
                acquireOtherData();
            }
        });
    }

    /**
     * 获取其他上报数据
     */
    private void acquireOtherData(){
        Log.e(TAG, "获取其他上报数据");
        executeUploadData();
    }

    /**
     * 执行数据上传
     */
    private void executeUploadData() {
        Log.e(TAG, "计算步数完成：" + mStepCount);
        Log.e(TAG, "数据获取完成");
        destroy();
    }

    /**
     * 上传结束，清理内存，停止相关服务
     */
    private void destroy() {
        Log.e(TAG, "上传结束，清理内存，停止相关服务");
        stepCountManager.destroy();
        destroyAlarmService();
    }


    private void destroyAlarmService(){
        Intent intent = new Intent(mContext, AlarmService.class);
        mContext.stopService(intent);
    }

}
