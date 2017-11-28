package com.example.fragment.theoryandpractice.recyclerViewPractice.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.fragment.theoryandpractice.R;
import com.example.fragment.theoryandpractice.recyclerViewPractice.adapter.DefaultSingleAdapter;
import com.example.fragment.theoryandpractice.recyclerViewPractice.view.DividerItemDecoration;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      15/9/15 上午12:10
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 15/9/15      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class SingleSelectFragment extends Fragment {
    @InjectView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    public SingleSelectFragment() {

    }

    public static SingleSelectFragment newInstance() {
        return new SingleSelectFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_normal, container, false);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));//这里用线性显示 类似于list view
        DefaultSingleAdapter adapter = new DefaultSingleAdapter(getActivity());
        ArrayList<String> items = new ArrayList<>();
        Collections.addAll(items, getActivity().getResources().getStringArray(R.array.titles));
        adapter.addItems(items);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
