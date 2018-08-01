package qktx.xinxiaolong.com.timetask;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
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
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText edJobId;
    Button btnStart;
    Button btnStop;
    Button btnStopAll;
    Button btnPrint;

    JobSchedulerManager jobSchedulerManager;

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

        edJobId=findViewById(R.id.edit_jobId);
        btnStart=findViewById(R.id.btn_startService);
        btnStop=findViewById(R.id.btn_stopService);
        btnStopAll=findViewById(R.id.btn_stopAllService);
        btnPrint=findViewById(R.id.btn_printAllService);

        btnStart.setOnClickListener(onClickListener);
        btnStop.setOnClickListener(onClickListener);
        btnStopAll.setOnClickListener(onClickListener);
        btnPrint.setOnClickListener(onClickListener);

        jobSchedulerManager=new JobSchedulerManager(this);
    }


    View.OnClickListener onClickListener=new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onClick(View v) {

            int jobId=Integer.parseInt(edJobId.getText().toString());

            switch (v.getId()){
                case R.id.btn_startService:
                    jobSchedulerManager.startDataSyncJobScheduler(jobId);
                    break;
                case R.id.btn_stopService:
                    jobSchedulerManager.stopDataSyncJobScheduler(jobId);
                    break;
                case R.id.btn_stopAllService:
                    jobSchedulerManager.stopAllDataSyncJobScheduler();
                    break;
                case R.id.btn_printAllService:
                    jobSchedulerManager.printAllPendingJobs();
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
}
