package com.huohuo.ui.splash;

import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dian.commonlib.app.Constants;
import com.dian.commonlib.base.BaseLoadActivity;
import com.dian.commonlib.glide.GlideEngine;
import com.dian.commonlib.utils.MmkvUtil;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.huohuo.BuildConfig;
import com.huohuo.R;
import com.huohuo.mvp.contract.splash.SplashContract;
import com.huohuo.mvp.model.bean.SplashBean;
import com.huohuo.mvp.presenter.splash.SplashPresenter;
import com.huohuo.ui.main.MainActivity;
import com.huohuo.ui.user.LoginActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * Created by kennysun on 2019/8/8.
 */

public class SplashActivity extends BaseLoadActivity implements SplashContract.View {
    @BindView(R.id.ivSplash)
    ImageView ivSplash;
    @BindView(R.id.ivDefault)
    ImageView ivDefault;
    @BindView(R.id.tvJump)
    TextView tvJump;
    private CountDownTimer countDownTimer;
    SplashPresenter splashPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void retry() {

    }

    @Override
    public MultipleStatusView getMultipleStatusView() {
        return null;
    }

    @Override
    public void initViewAndData() {
        super.initViewAndData();
        GlideEngine.load(ivDefault, R.drawable.splash_img);
        splashPresenter = new SplashPresenter();
        splashPresenter.attachView(this, this);
        //延时一秒获取
        Observable.timer(1,TimeUnit.SECONDS)
                .subscribe(aLong -> splashPresenter.splash());

    }

    @OnClick(R.id.tvJump)
    public void onViewClicked() {
        if (countDownTimer != null)
            countDownTimer.onFinish();
    }

    @Override
    public void splash(SplashBean data) {
        if ("1".equals(data.getImageOpen())) {//开机图启动
            ivSplash.setVisibility(View.VISIBLE);
            tvJump.setVisibility(View.VISIBLE);
            String time = data.getImageTime();
            GlideEngine.loadNoPlaceholder(ivSplash, BuildConfig.API_IMG_HOST+data.getImageLink());
            tvJump.setVisibility(View.VISIBLE);
            countDownTimer = new CountDownTimer(Long.parseLong(time) * 1000L, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    if (!tvJump.isEnabled()) return;
                    tvJump.setText(getResources().getString(R.string.jump) + (millisUntilFinished / 1000));
                }

                @Override
                public void onFinish() {
                    if (!tvJump.isEnabled()) return;
                    tvJump.setEnabled(false);
                    tvJump.setText(getResources().getString(R.string.jump));
                    GlideEngine.stopLoad(ivSplash);
                    Observable.timer(1, TimeUnit.SECONDS)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(aLong -> next());
                }
            };
            countDownTimer.start();
        } else {
            next();
        }
    }

    @Override
    public void next() {
        String token = MmkvUtil.decodeString(Constants.TOKEN, "");
        if (token.isEmpty()) {
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            startActivity(new Intent(this, MainActivity.class));
        }
        finish();
    }

}
