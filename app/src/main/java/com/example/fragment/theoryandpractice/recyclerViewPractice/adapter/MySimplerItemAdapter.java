package com.example.fragment.theoryandpractice.recyclerViewPractice.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fragment.theoryandpractice.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Created by xiaolong on 16/8/2.
 */
public class MySimplerItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<String> listData = new ArrayList<>();

    public List<String> getListData() {
        return listData;
    }

    public void setListData(List<String> listData) {
        this.listData = listData;
    }

    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public enum ViewType {
        UNKNOWN,
        TEXT,
        IMAGE;

        public static ViewType valueOf(int ordinal) {
            if (ordinal < 0 || ordinal >= values().length) {
                return UNKNOWN;
            }
            return values()[ordinal];
        }
    }


    public MySimplerItemAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewType type = ViewType.valueOf(viewType);
        ViewHolder viewHolder = null;
        switch (type) {
            case TEXT:
                viewHolder = new TextViewHolder(mLayoutInflater.inflate(R.layout.include_text_view_recycler_layout, parent, false));
                break;
            case IMAGE:
                viewHolder = new ImageViewHolder(mLayoutInflater.inflate(R.layout.include_img_view_recycler_layout, parent, false));
                break;
        }
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        String text=listData.get(position);
        int viewType = getItemViewType(position);
        ViewType type = ViewType.valueOf(viewType);
        switch (type){
            case TEXT:
                ((TextViewHolder)viewHolder).iniViewData(text);
                break;
            case IMAGE:

                break;
        }
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position % 3 == 0 ? ViewType.IMAGE.ordinal() : ViewType.TEXT.ordinal();
    }

    public class TextViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.text)
        TextView textView;

        public TextViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        public void iniViewData(String text){
            textView.setText(text);
        }
    }


    public class ImageViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.image)
        ImageView image;

        public ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}


