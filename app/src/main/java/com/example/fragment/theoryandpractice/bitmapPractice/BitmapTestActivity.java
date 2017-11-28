package com.example.fragment.theoryandpractice.bitmapPractice;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.fragment.theoryandpractice.R;
import com.example.fragment.theoryandpractice.widget.MyButton;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by xiaolong.
 * on 2016-01-29 下午3:13.
 */
public class BitmapTestActivity extends Activity{

    @InjectView(R.id.rela_content)
    RelativeLayout rela_content;
    @InjectView(R.id.img_bitmpTest)
    ImageView img_bitmpTest;
    @InjectView(R.id.btn_addImage)
    Button btn_addImage;
    @InjectView(R.id.btn_removeImage)
    Button btn_removeImage;

    Bitmap bitmap;
    Bitmap bitmap1;
    Bitmap bitmap2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bitmap_layout);
        ButterKnife.inject(this);
        btn_addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.homeself_bg);

                img_bitmpTest.setImageBitmap(bitmap);
            }
        });

        btn_removeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rela_content.removeView(img_bitmpTest);
                if(bitmap!=null&&!bitmap.isRecycled()){
                    bitmap.recycle();
                    bitmap=null;
                }
                if(bitmap1!=null&&!bitmap1.isRecycled()){
                    bitmap1.recycle();
                    bitmap1=null;
                }
                if(bitmap2!=null&&!bitmap2.isRecycled()){
                    bitmap2.recycle();
                    bitmap2=null;
                }
                System.gc();
            }
        });
    }
}
