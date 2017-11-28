package mark.org.servicedemo;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * 常驻服务
 */
public class AlwaysLiveService extends Service {
    public static final String TAG = "AlwaysLiveService";

    public AlwaysLiveService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(R.id.fake_notify, new Notification());
//        startService(new Intent(this, FakeService.class));
        Log.d(TAG, "AlwaysLiveService====>");

        new Thread(new Runnable() {
            int i = 0;

            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        i++;
                        Log.d(TAG, "AlwaysLiveService==stat Sum ==>" + i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

}
