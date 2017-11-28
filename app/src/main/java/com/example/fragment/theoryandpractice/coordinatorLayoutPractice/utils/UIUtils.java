package com.example.fragment.theoryandpractice.coordinatorLayoutPractice.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fragment.theoryandpractice.PracticeApplication;


/**
 * Created by Administrator on 2015/11/3.
 */
public class UIUtils {
    /**
     * 获取上下文
     */

    public static Context getContext() {
        return PracticeApplication.getContext();
    }
    /** 获取资源 */
    public static Resources getResources() {

        return getContext().getResources();
    }

    /**
     * FindViewById的泛型封装
     */
    public static <T extends View> T findViewById(View layout, int id) {
        return (T) layout.findViewById(id);
    }
    /**
     * 获取颜色
     */
    public static int getColor(int color){
        return getResources().getColor(color);
    }
    /**
     * 获取dimen文件
     */
    public static int getDimen(int dimen){
        return (int) getResources().getDimension(dimen);
    }

    /**
     * 设置弹框出现的时候背景变暗
     */
    public static void setParentView(final Activity activity, PopupWindow popupWindow){
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = 0.7f;
        activity.getWindow().setAttributes(lp);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                lp.alpha = 1f;
                activity.getWindow().setAttributes(lp);
            }
        });
    }
    /**
     * 设置弹出toas
     */
    public static void showToast(String str){
        Toast.makeText(getContext(),str,Toast.LENGTH_SHORT).show();
    }
    /**
     * 设置log
     */
    public static void log(String str){
        android.util.Log.i("zzz", str);
    }
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px( float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(float pxValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 切换键盘
     */
    public static void switchKeyBoard(){
        ((InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).
                toggleSoftInput(0, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }
    /**
     * 隐藏键盘
     */
    public static void hideKeyboard(EditText v) {
        ((InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(v.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
    }
    /**
     * 弹出键盘
     * @param et
     */
    public static void showKeyboard(EditText et) {
        ((InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                .showSoftInputFromInputMethod(et.getWindowToken(), 0);
    }

}
