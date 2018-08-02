package qktx.xinxiaolong.com.timetask;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import qktx.xinxiaolong.com.timetask.alarm.AlarmJobManager;
import qktx.xinxiaolong.com.timetask.jobservice.DaemonAlarmJobManager;

public class MainActivity extends AppCompatActivity {


    public static String TAG = "TIME_TASK";
    private StringBuilder logStrBuilder = new StringBuilder();
    private TimerServiceManager timerServiceManager;

    EditText edJobId;
    Button btnStart;
    Button btnStop;
    Button btnStopAll;
    Button btnPrint;
    TextView tvLog;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        EventBus.getDefault().register(this);


        edJobId = findViewById(R.id.edit_jobId);
        btnStart = findViewById(R.id.btn_startService);
        btnStop = findViewById(R.id.btn_stopService);
        btnStopAll = findViewById(R.id.btn_stopAllService);
        btnPrint = findViewById(R.id.btn_printAllService);
        tvLog = findViewById(R.id.tv_log);

        btnStart.setOnClickListener(onClickListener);
        btnStop.setOnClickListener(onClickListener);
        btnStopAll.setOnClickListener(onClickListener);
        btnPrint.setOnClickListener(onClickListener);

        timerServiceManager=TimerServiceManager.get(this);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.btn_startService:
                    timerServiceManager.startTimerService(15000);
                    break;
                case R.id.btn_stopService:
                    timerServiceManager.stopTimeService();
                    break;
                case R.id.btn_stopAllService:
                    timerServiceManager.stopTimeService();
                    break;
                case R.id.btn_printAllService:

                    break;
            }
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventBus(String event) {
        logStrBuilder.append(event + "\n");
        tvLog.setText(logStrBuilder.toString());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "onDestroy");
    }
}
