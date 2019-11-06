package com.dian.commonlib.utils;

import android.os.CountDownTimer;
import android.widget.Button;

import com.dian.commonlib.R;

/**
 * Created by kennysun on 2019/8/29.
 */

public class CountDownTimerUtil extends CountDownTimer {
    private Button mButton;

    /**
     * @param button            The TextView
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receiver
     *                          {@link #onTick(long)} callbacks.
     */
    public CountDownTimerUtil(Button button, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.mButton = button;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        mButton.setEnabled(false); //设置不可点击
        mButton.setText(millisUntilFinished / 1000 + mButton.getContext().getResources().getString(R.string.m_send_again));  //设置倒计时时间60秒后重发
    }

    @Override
    public void onFinish() {
        mButton.setText(R.string.send_again);
        mButton.setEnabled(true);//重新获得点击
    }
}