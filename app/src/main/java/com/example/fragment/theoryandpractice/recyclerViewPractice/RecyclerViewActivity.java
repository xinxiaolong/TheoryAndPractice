package com.example.fragment.theoryandpractice.recyclerViewPractice;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.fragment.theoryandpractice.R;
import com.example.fragment.theoryandpractice.recyclerViewPractice.adapter.MySimplerItemAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by xiaolong on 16/8/2.
 */
public class RecyclerViewActivity extends Activity {

    @InjectView(R.id.my_recycler_view)
    RecyclerView myRecyclerView;

    MySimplerItemAdapter simplerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_layout);
        ButterKnife.inject(this);
        initListData();
        myRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));
        myRecyclerView.setAdapter(simplerAdapter);
    }

    private void initListData(){
        List<String>  list=new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add(i+"----------");
        }

        simplerAdapter=new MySimplerItemAdapter(this);
        simplerAdapter.setListData(list);
    }


}
