package com.example.fragment.theoryandpractice.httpPractice;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fragment.theoryandpractice.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by xiaolong.
 * on 2016-01-01 下午2:44.
 */
public class httpActivity extends Activity {


    @InjectView(R.id.btn_startDownload)
    Button btnStartDownload;

    String downlaodUrl="http://g.hiphotos.baidu.com/baike/w%3D268/sign=66d17ed667380cd7e61ea5eb9945ad14/e61190ef76c6a7ef18b42940fffaaf51f2de66c2.jpg";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        ButterKnife.inject(this);
        btnStartDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Downloader downloader=new Downloader();
                downloader.toDownload(downlaodUrl,downloaderListenner);
            }
        });
    }

    Downloader.DownloaderListenner downloaderListenner=new Downloader.DownloaderListenner() {
        @Override
        public void onStart() {
            btnStartDownload.setText("开始了...");
        }

        @Override
        public void onError(String msg) {
            btnStartDownload.setText("出错了"+msg);
        }

        @Override
        public void onProcess(int totalLenght, int curLenght) {
            btnStartDownload.setText("下载中"+curLenght/1000+"/"+totalLenght/1000);
        }

        @Override
        public void onComplete() {
            btnStartDownload.setText("下载完成");
        }
    };
}
