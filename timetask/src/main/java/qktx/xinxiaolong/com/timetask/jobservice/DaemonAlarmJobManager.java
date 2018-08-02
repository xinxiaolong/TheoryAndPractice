package qktx.xinxiaolong.com.timetask.jobservice;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.util.List;

import qktx.xinxiaolong.com.timetask.alarm.AlarmJobManager;

import static android.content.Context.JOB_SCHEDULER_SERVICE;

/**
 * Created by xiaolong on 2018/8/1.
 * email：xinxiaolong123@foxmail.com
 */

public class DaemonAlarmJobManager {

    static long DATA_SYNC_INTERVAL=10000;

    public static String TAG="TIME_TASK";

    private static DaemonAlarmJobManager jobSchedulerManager;
    Context mContext;


    private DaemonAlarmJobManager(){


    }

    private DaemonAlarmJobManager init(Context context){
        mContext=context.getApplicationContext();
        return this;
    }

    public static DaemonAlarmJobManager get(Context context){
        if(jobSchedulerManager==null){
            jobSchedulerManager=new DaemonAlarmJobManager();
        }
        return jobSchedulerManager.init(context);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void startDataSyncJobScheduler(int jobId) {
        ComponentName jobService = new ComponentName(mContext, DaemonAlarmJobService.class);

        JobInfo.Builder builder = new JobInfo.Builder(jobId, jobService);
        builder.setPeriodic(DATA_SYNC_INTERVAL)
                .setPersisted(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);

        JobScheduler js = (JobScheduler)mContext.getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode=js.schedule(builder.build());

        String msg=resultCode==1?"启动服务成功":"启动服务失败";
        Log.e(TAG,msg);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void stopDataSyncJobScheduler(int jobId){
        JobScheduler js = (JobScheduler)mContext.getSystemService(JOB_SCHEDULER_SERVICE);
        js.cancel(jobId);
        Log.e(TAG,"停止了服务 jobId="+jobId);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void stopAllDataSyncJobScheduler(){
        JobScheduler js = (JobScheduler)mContext.getSystemService(JOB_SCHEDULER_SERVICE);
        js.cancelAll();
        Log.e(TAG,"停止所有服务");

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void printAllPendingJobs(){
        JobScheduler js = (JobScheduler)mContext.getSystemService(JOB_SCHEDULER_SERVICE);
        List<JobInfo> jobInfoList=js.getAllPendingJobs();

        for (int i=0;i<jobInfoList.size();i++){
            JobInfo jobInfo=jobInfoList.get(i);
            int id=jobInfo.getId();
            String name=jobInfo.getService().getClassName();
            Log.e(TAG,"id="+id+"   name="+name);

        }
    }
}
