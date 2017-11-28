package com.example.fragment.theoryandpractice.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.fragment.theoryandpractice.R;

/**
 * 通用加载对话框，可自定义显示百分比
 * Created by xujian on 2015/7/27.
 */
public class LoadingDialog extends Dialog {
    private View dialogView;
    private TextView msg;
    private ProgressBar progressBar;

    public LoadingDialog(Context context) {
        super(context, R.style.progress_dialog);
        init(context);
    }

    public LoadingDialog(Context context, int theme) {
        super(context, theme);
        init(context);
    }

    protected LoadingDialog(Context context, boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    private void init(Context context) {
        dialogView = View.inflate(context, R.layout.loading_dialog, null);
        this.setContentView(dialogView);
        this.setCancelable(true);
        this.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        msg = (TextView) findViewById(R.id.id_tv_loadingmsg);


        getWindow();

    }

    public void setText(String text) {
        msg.setText(text);
    }

    public void setWidth(int width, int height) {
        if (dialogView == null)
            return;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        dialogView.setLayoutParams(params);
    }
}
