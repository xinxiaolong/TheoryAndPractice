package com.example.fragment.theoryandpractice.collectionPratice.model;

/**
 * Created by xiaolong on 2017/10/25.
 * email：xinxiaolong123@foxmail.com
 */

public abstract class RequestCallBack<T> {

    public abstract void onSuccess(T t);

    public abstract void onFail(int code, String msg);

}
