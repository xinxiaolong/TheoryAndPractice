package com.example.fragment.theoryandpractice.coordinatorLayoutPractice.fragment;


import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fragment.theoryandpractice.R;


public class FragmentTwo extends Fragment {


    private static FragmentTwo instance=null;
    private LayoutInflater mInflater = null;


    public static FragmentTwo newInstance() {
        if(instance==null){
            instance= new FragmentTwo();
        }
        return instance;
    }
    public FragmentTwo(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_two, container, false);
        ListView listView=(ListView) view.findViewById(R.id.listView);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            listView.setNestedScrollingEnabled(true);
        }
        mInflater=LayoutInflater.from(getActivity());
        listView.setAdapter(adapter);

        return view;
    }

    BaseAdapter adapter=new BaseAdapter() {

        @Override
        public int getCount() {
            return 100;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = mInflater.inflate(R.layout.include_text_view_recycler_layout, null);
            TextView textView=(TextView) convertView.findViewById(R.id.text);
            textView.setText(position+"");
            return convertView;
        }
    };


}
