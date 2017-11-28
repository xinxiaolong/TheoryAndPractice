package com.example.fragment.theoryandpractice.fragmentPractice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fragment.theoryandpractice.R;
import com.example.fragment.theoryandpractice.utils.ApUtils;

/**
 * Created by xiaolong on 16/8/2.
 */
public class FragmentFirst extends Fragment{

    View contentView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView= inflater.inflate(R.layout.fragment_first_layout, container, false);
        return contentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    public void onBackPressed(){
        ApUtils.showToast("点击了返回");
    }



}
