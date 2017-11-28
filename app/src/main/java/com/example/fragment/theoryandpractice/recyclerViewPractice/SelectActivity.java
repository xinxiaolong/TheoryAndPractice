package com.example.fragment.theoryandpractice.recyclerViewPractice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.fragment.theoryandpractice.R;
import com.example.fragment.theoryandpractice.recyclerViewPractice.fragment.MultipleSelectFragment;
import com.example.fragment.theoryandpractice.recyclerViewPractice.fragment.SingleSelectFragment;

import butterknife.ButterKnife;


public class SelectActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_activity_main);
        ButterKnife.inject(this);
        int index = getIntent().getIntExtra("position", 0);
        Log.d("NormalTextViewHolder", "onClick--> index = " + index);
        String title = getIntent().getStringExtra("title");
        setTitle(title);
        updateFragment(index);
    }

    private void updateFragment(int index) {
        if (index == 11) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, SingleSelectFragment.newInstance())
                    .commit();
        } else if (index == 12) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MultipleSelectFragment.newInstance())
                    .commit();
        }
    }
}
