package com.huohuo.ui.web;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dian.commonlib.base.BaseWebViewActivity;
import com.dian.commonlib.utils.LogUtil;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.dian.commonlib.utils.widget.MyDialog;
import com.huohuo.R;
import com.huohuo.app.HuoHuoConstants;
import com.huohuo.mvp.contract.sys.HuoHuoWebViewContract;
import com.huohuo.mvp.presenter.sys.HuoHuoWebViewPresenter;
import com.huohuo.ui.spread.SpreadIntroduceActivity;
import com.huohuo.ui.sys.PayDialogFragment;
import com.huohuo.ui.user.MobileCodeActivty;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kennysun on 2019/8/29.
 */

public class HuoHuoWebViewActivity extends BaseWebViewActivity implements HuoHuoWebViewContract.View {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.ivLeft1)
    ImageView ivLeft1;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.webDetails)
    WebView webDetails;
    @BindView(R.id.multipleStatusView)
    MultipleStatusView multipleStatusView;
    private PayDialogFragment payDialogFragment;
    private String pwdStr;
    private HuoHuoWebViewPresenter huoHuoWebViewPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_webview;
    }

    @Override
    public void spread() {
        startActivity(new Intent(this, SpreadIntroduceActivity.class));
    }

    @Override
    protected void h5Pay() {
        Bundle params = new Bundle();
        params.putSerializable(HuoHuoConstants.PAY_TYPE, HuoHuoConstants.PayType.GAMEGO);
        payDialogFragment = PayDialogFragment.newInstance(params);
        payDialogFragment.setPayDialogCallback(new PayDialogFragment.PayDialogCallback() {
            @Override
            public void getPwd(String pwd) {
                //验证密码
                pwdStr = pwd;
                huoHuoWebViewPresenter.checkPayPwd(pwd);
            }
        });
        payDialogFragment.show(getSupportFragmentManager(), PayDialogFragment.class.getSimpleName());
    }

    @Override
    public ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public WebView getWebView() {
        return webDetails;
    }

    @Override
    protected void upDateTitle(String titleStr) {
        tvTitle.setText(titleStr);
    }

    @Override
    public void retry() {

    }

    @Override
    public MultipleStatusView getMultipleStatusView() {
        return multipleStatusView;
    }

    @Override
    public void initViewAndData() {
        super.initViewAndData();
        ivLeft.setVisibility(View.VISIBLE);
        ivLeft1.setVisibility(View.VISIBLE);
        huoHuoWebViewPresenter = new HuoHuoWebViewPresenter();
        huoHuoWebViewPresenter.attachView(this, this);

    }

    @OnClick({R.id.ivLeft, R.id.ivLeft1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                back();
                break;
            case R.id.ivLeft1:
                close();
                break;
        }
    }

    @Override
    public void checkOldPwdSuccess() {
        payDialogFragment.dismiss();
        LogUtil.d("pwdStr === " + pwdStr + "\n" + "token === " + token);
        webDetails.loadUrl("javascript:pay('" + pwdStr + "','" + token + "')");
    }

    @Override
    public void checkOldPwdError() {
        payDialogFragment.clear();

    }

    /**
     * 支付密码被冻结
     *
     * @param msg
     */
    @Override
    public void showPwdTipDialog(Object msg) {
        String title;
        if (msg instanceof Integer) {
            title = getResources().getString((Integer) msg);
        } else {
            title = (String) msg;
        }
        new MyDialog.Builder(this)
                .setMessage(title)
                .setNegativeButton(R.string.forget_pay_pwd, (dialog, which) -> {
                    Intent intent = new Intent(HuoHuoWebViewActivity.this, MobileCodeActivty.class);
                    intent.putExtra("type", HuoHuoConstants.PayPwdType.find_sms);
                    startActivity(intent);
                    payDialogFragment.dismiss();
                }).setPositiveButton(R.string.yes_i_know, (dialog, which) -> dialog.dismiss()).show();
    }

}
