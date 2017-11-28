package com.example.fragment.theoryandpractice.recyclerViewPractice;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;

import com.example.fragment.theoryandpractice.R;
import com.example.fragment.theoryandpractice.recyclerViewPractice.fragment.ItemsFragment;

import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_activity_main);
        ButterKnife.inject(this);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ItemsFragment.newInstance())
                    .commit();
        }
    }
}
