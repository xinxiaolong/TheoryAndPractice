package com.example.fragment.theoryandpractice.IntentPractice;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.fragment.theoryandpractice.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by xiaolong on 15/11/13.
 */
public class ActivityTwo extends Activity{

    @InjectView(R.id.tvName)
    TextView tvName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        ButterKnife.inject(this);
        tvName.setText("ActivityTwo");
    }

}
