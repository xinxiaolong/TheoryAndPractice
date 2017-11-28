package com.example.fragment.theoryandpractice.advert;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.io.File;

/**
 * Created by xiaolong on 2017/10/18.
 * email：xinxiaolong123@foxmail.com
 *
 * 广告的加载器
 *
 * 主要功能：
 * 1: 获取广告的信息
 * 2: 下载广告的资源
 * 3: 是都需要播放广告监听
 */

public class AdvertLoader {

    private Context context;                           //上下文
    private AdvConfigModel advConfigModel;             //广告配置信息
    private NeedPlayAdvListener needPlayAdvListener;   //是否要播放广告
    private Activity main;                             //跳转的主界面
    private Activity adv;                              //跳转广告界面
    private boolean started = false;                   //是否已经跳转


    public AdvertLoader(Context context, AdvConfigModel advConfigModel) {
        this.context = context;
        this.advConfigModel = advConfigModel;
    }


    /**
     * 启动自动加载广告模式
     * @param main
     * @param adv
     */
    public void startAutoLaunch(Activity main, Activity adv) {
        setUpLaunchActivity(main, adv);
        setNeedPlayAdvListener(new NeedPlayAdvListener() {
            @Override
            public void OnNeedPlay(AdvConfigModel advConfigModel) {
                startAdvActivity();
            }

            @Override
            public void OnNeedNotPlay() {
                startMainActivity();
            }
        });
        handleAdvConfig(advConfigModel);
    }


    /**
     * 启动加载广告
     * @param needPlayAdvListener
     */
    public void startLoad(NeedPlayAdvListener needPlayAdvListener) {
        setNeedPlayAdvListener(needPlayAdvListener);
        handleAdvConfig(advConfigModel);
    }

    /**
     * 处理广告配置信息
     *
     * @param advConfigModel
     */
    private void handleAdvConfig(AdvConfigModel advConfigModel) {
        this.advConfigModel = advConfigModel;
        if (advConfigModel != null && advConfigModel.needPlay) {
            downloadImage(advConfigModel.imageUrl);
        } else {
            needPlayAdvListener.OnNeedNotPlay();
        }
    }

    /**
     * 下载广告图片
     *
     * @param url
     */

    private void downloadImage(String url) {

        RequestBuilder<File> drawable = Glide.with(context).download(url);
        drawable.listener(new RequestListener<File>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<File> target, boolean isFirstResource) {
                if (needPlayAdvListener != null && !started) {
                    started = true;
                    needPlayAdvListener.OnNeedNotPlay();
                }
                return false;
            }

            @Override
            public boolean onResourceReady(File resource, Object model, Target<File> target, DataSource dataSource, boolean isFirstResource) {
                if (needPlayAdvListener != null && !started) {
                    started = true;
                    needPlayAdvListener.OnNeedPlay(advConfigModel);
                }
                return false;
            }
        });
        drawable.submit();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(started){
                    return;
                }
                started = true;
                needPlayAdvListener.OnNeedPlay(advConfigModel);
            }
        }, 1000);
    }

    public interface NeedPlayAdvListener {
        void OnNeedPlay(AdvConfigModel advConfigModel);

        void OnNeedNotPlay();
    }

    private void setUpLaunchActivity(Activity main, Activity adv) {
        this.main = main;
        this.adv = adv;
    }

    private void setNeedPlayAdvListener(NeedPlayAdvListener needPlayAdvListener) {
        this.needPlayAdvListener = needPlayAdvListener;
    }

    private void startAdvActivity() {

    }

    private void startMainActivity() {

    }
}
