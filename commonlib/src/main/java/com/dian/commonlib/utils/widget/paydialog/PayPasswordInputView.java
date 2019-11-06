package com.dian.commonlib.utils.widget.paydialog;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dian.commonlib.R;
import com.dian.commonlib.utils.LogUtil;

/**
 * Created by kennysun on 2019/8/29.
 */

public class PayPasswordInputView extends LinearLayout {
    public PayPasswordInputView(Context context) {
        this(context, null);
    }

    public PayPasswordInputView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    private TextView tvOne;
    private TextView tvTwo;
    private TextView tvThree;
    private TextView tvFour;
    private TextView tvFive;
    private TextView tvSix;
    private String value;
    private int mLength;

    public PayPasswordInputView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_pay_password_input, this);
        tvOne = view.findViewById(R.id.tvOne);
        tvTwo = view.findViewById(R.id.tvTwo);
        tvThree = view.findViewById(R.id.tvThree);
        tvFour = view.findViewById(R.id.tvFour);
        tvFive = view.findViewById(R.id.tvFive);
        tvSix = view.findViewById(R.id.tvSix);
    }

    //清空密码框
    public void clear() {
        value = "";
        mLength = 0;
        //清空密码
        tvOne.setText("");
        tvTwo.setText("");
        tvThree.setText("");
        tvFour.setText("");
        tvFive.setText("");
        tvSix.setText("");

    }

    public void setValue(String value) {
        LogUtil.d("setValue===" + value);
        int length = value == null ? 0 : value.length();
        if (length > 6) {
            this.value = value.substring(0, 6);
            mLength = 6;
        } else {
            this.value = value;
            mLength = length;
        }
        resetLength();
        if (mLength == 6 && this.onPasswordCompleteCallback != null) {
            this.onPasswordCompleteCallback.onPasswordComplete(this.value);
        }
    }

    private void resetLength() {
        tvOne.setText("");
        tvTwo.setText("");
        tvThree.setText("");
        tvFour.setText("");
        tvFive.setText("");
        tvSix.setText("");
        if (mLength > 0) {
            tvOne.setText("*");
        }
        if (mLength > 1) {
            tvTwo.setText("*");
        }
        if (mLength > 2) {
            tvThree.setText("*");
        }
        if (mLength > 3) {
            tvFour.setText("*");
        }
        if (mLength > 4) {
            tvFive.setText("*");
        }
        if (mLength > 5) {
            tvSix.setText("*");
        }
    }

    private OnPasswordCompleteCallback onPasswordCompleteCallback;

    public void setOnPasswordCompleteCallback(OnPasswordCompleteCallback onPasswordCompleteCallback) {
        this.onPasswordCompleteCallback = onPasswordCompleteCallback;
    }

    public interface OnPasswordCompleteCallback {
        void onPasswordComplete(String value);
    }
}