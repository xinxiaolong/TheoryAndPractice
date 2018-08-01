package qktx.xinxiaolong.com.timetask.jobservice;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.util.Log;

/**
 * Created by xiaolong on 2018/8/1.
 * email：xinxiaolong123@foxmail.com
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class DataSyncJobService extends JobService {

    public static String TAG="TIME_TASK";
    @Override
    public boolean onStartJob(JobParameters params) {
        int jobId=params.getJobId();
        Log.v(TAG,"onStartJob()   jobId="+jobId+"     "+Thread.currentThread().getName());

        Message message=Message.obtain();
        message.obj=params;
        handler.sendMessageDelayed(message,15000);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        int jobId=params.getJobId();
        Log.v(TAG,"onStopJob()    jobId="+jobId+"     "+Thread.currentThread().getName());
        return false;
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            JobParameters params=(JobParameters) msg.obj;
            //Log.v(TAG,"onStartJob()   jobId="+params.getJobId()+"     任务执行完毕。");
            jobFinished(params,false);
        }
    };
}
