package com.example.fragment.theoryandpractice.fragmentPractice;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.LinearLayout;

import com.example.fragment.theoryandpractice.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by xiaolong on 16/8/2.
 */
public class MainFragmentActivity extends FragmentActivity {

    @InjectView(R.id.linear_content)
    LinearLayout linearContent;


    FragmentFirst fragmentFirst=new FragmentFirst();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_main_layout);
        ButterKnife.inject(this);
        setFragmentFirst();
    }


    private void setFragmentFirst(){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.linear_content, fragmentFirst);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        fragmentFirst.onBackPressed();
        //super.onBackPressed();
    }
}
