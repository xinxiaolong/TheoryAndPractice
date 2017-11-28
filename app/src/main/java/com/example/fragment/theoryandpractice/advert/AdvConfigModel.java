package com.example.fragment.theoryandpractice.advert;

import java.io.Serializable;

/**
 * Created by xiaolong on 2017/10/18.
 * email：xinxiaolong123@foxmail.com
 */

public class AdvConfigModel implements Serializable{

    public boolean needPlay;       //是否要播放广告
    public String imageUrl;        //广告图片
    public String url;             //点击广告加载的url
    public long showTime;          //广告播放时间

}
