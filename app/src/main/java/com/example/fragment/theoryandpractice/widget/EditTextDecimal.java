package com.example.fragment.theoryandpractice.widget;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by xiaolong on 2016/12/12.
 */

public class EditTextDecimal extends EditText {

    private final int RETAIN_DIGIT=1;//小数点后保留几位

    public EditTextDecimal(Context context) {
        super(context);
    }

    public EditTextDecimal(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        initEvent();
    }

    private void initEvent() {
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                if (text.contains(".")) {
                    int index = text.indexOf(".");
                    if (index + RETAIN_DIGIT+1< text.length()) {
                        text = text.substring(0, index + RETAIN_DIGIT+1);
                        setText(text);
                        setSelection(text.length());
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
