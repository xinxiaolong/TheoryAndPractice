package com.example.fragment.theoryandpractice.recyclerViewPractice.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.fragment.theoryandpractice.R;
import com.example.fragment.theoryandpractice.recyclerViewPractice.FullyLinearLayoutManager;
import com.example.fragment.theoryandpractice.recyclerViewPractice.adapter.AnimAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      2015/2/6  14:42.
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2015/2/6        ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class FullyExpandedFragment extends Fragment {
    public static final int TYPE_LINEAR_LAYOUT = 1;
    public static final int TYPE_GRID_LAYOUT = 2;
    public static final int TYPE_STAGGERED_GRID_LAYOUT = 3;
    @InjectView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private int type = TYPE_LINEAR_LAYOUT;
    private AnimAdapter mAdapter;

    public static FullyExpandedFragment newInstance(int type) {
        FullyExpandedFragment fragment = new FullyExpandedFragment();
        Bundle args = new Bundle();
        args.putInt("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getInt("type", TYPE_LINEAR_LAYOUT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fully_expended, container, false);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (type == TYPE_GRID_LAYOUT) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));//这里用线性宫格显示 类似于grid view
        } else if (type == TYPE_STAGGERED_GRID_LAYOUT) {
            mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));//这里用线性宫格显示 类似于瀑布流
        } else {
           // mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));//这里用线性显示 类似于list view V23.2.0 支持自动计算高度大小，也就是可以嵌套了
            mRecyclerView.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        }
        mAdapter = new AnimAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] titles = getResources().getStringArray(R.array.titles);
        mAdapter.addTitles(titles);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
