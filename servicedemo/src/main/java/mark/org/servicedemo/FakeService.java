package mark.org.servicedemo;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;


/**
 * 临时服务
 */
public class FakeService extends Service {
    public FakeService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(R.id.fake_notify, new Notification());
        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        stopForeground(true);
        super.onDestroy();
    }
}
