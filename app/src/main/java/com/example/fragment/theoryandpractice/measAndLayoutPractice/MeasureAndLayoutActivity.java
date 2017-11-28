package com.example.fragment.theoryandpractice.measAndLayoutPractice;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fragment.theoryandpractice.R;
import com.example.fragment.theoryandpractice.widget.AutoCardLayout;
import com.example.fragment.theoryandpractice.widget.CardImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by xiaolong.
 * on 2016-01-09 下午3:55.
 */
public class MeasureAndLayoutActivity extends Activity{

    Context context;
    @InjectView(R.id.autoCardLayout)
    AutoCardLayout autoCardLayout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measure_layout);
        ButterKnife.inject(this);
        context=this;
        addChildView();

        autoCardLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"点击了内容",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addChildView() {
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        ImageView imageView0 = new CardImageView(this);
        imageView0.setBackgroundResource(R.drawable.refund_icon_34_34);
        autoCardLayout.addView(imageView0, params);

        ImageView imageView1 = new CardImageView(this);
        imageView1.setBackgroundResource(R.drawable.img9);
        autoCardLayout.addView(imageView1, params);

        ImageView imageView2 = new CardImageView(this);
        imageView2.setBackgroundResource(R.drawable.refund_icon_34_34);
        autoCardLayout.addView(imageView2, params);

        ImageView imageView3 = new CardImageView(this);
        imageView3.setBackgroundResource(R.drawable.refund_icon_34_34);
        autoCardLayout.addView(imageView3, params);

        ImageView imageView4 = new CardImageView(this);
        imageView4.setBackgroundResource(R.drawable.refund_icon_34_34);
        autoCardLayout.addView(imageView4, params);

        ImageView imageView5 = new CardImageView(this);
        imageView5.setBackgroundResource(R.drawable.refund_icon_34_34);
        autoCardLayout.addView(imageView5, params);

        ImageView imageView6 = new CardImageView(this);
        imageView6.setBackgroundResource(R.drawable.img6);
        autoCardLayout.addView(imageView6, params);

        ImageView imageView7 = new CardImageView(this);
        imageView7.setBackgroundResource(R.drawable.img2);
        autoCardLayout.addView(imageView7, params);

        ImageView imageView8 = new CardImageView(this);
        imageView8.setBackgroundResource(R.drawable.img7);
        autoCardLayout.addView(imageView8, params);

        ImageView imageView9 = new CardImageView(this);
        imageView9.setBackgroundResource(R.drawable.img1);
        autoCardLayout.addView(imageView9, params);

        for (int i = 0; i < 10; i++) {
            ImageView imageView = new CardImageView(this);
            imageView.setBackgroundResource(R.drawable.img1);
            autoCardLayout.addView(imageView, params);
        }

        for (int i = 0; i < 6; i++) {
            ImageView imageView = new CardImageView(this);
            imageView.setBackgroundResource(R.drawable.img6);
            autoCardLayout.addView(imageView, params);
        }
    }
}
