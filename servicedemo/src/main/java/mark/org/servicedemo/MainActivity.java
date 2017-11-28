package mark.org.servicedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.tv_start_service)
    public void startCustomService() {
        startService(new Intent(this, AlwaysLiveService.class));
    }

    @OnClick(R.id.tv_stop_service)
    public void stopCustomService() {
        stopService(new Intent(this, AlwaysLiveService.class));
        startActivity(new Intent(this, SecondActivity.class));
    }
}
