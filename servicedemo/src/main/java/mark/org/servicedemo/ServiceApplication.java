package mark.org.servicedemo;

import android.app.Application;
import android.util.Log;

/**
 * Created by mark.wu on 16/9/1.
 */
public class ServiceApplication extends Application {
    public static final String TAG = "ServiceApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "ServiceApplication start");
        Foreground.init(this);

        Foreground.get().addListener(new Foreground.Listener() {
            @Override
            public void onBecameForeground() {
                Log.d(TAG, "onBecameForeground=====>fg");
            }

            @Override
            public void onBecameBackground() {
                Log.d(TAG, "onBecameBackground=====>bg");
            }
        });
    }

}
