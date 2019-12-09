package com.yjb.ui.user;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.dian.commonlib.app.Constants;
import com.dian.commonlib.base.BaseLoadActivity;
import com.dian.commonlib.utils.CountDownTimerUtil;
import com.dian.commonlib.utils.ToastUtil;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.dian.commonlib.utils.widget.OnTextChangeListener;
import com.yjb.BuildConfig;
import com.yjb.R;
import com.yjb.app.HuoHuoConstants;
import com.yjb.mvp.contract.user.RegistContract;
import com.yjb.mvp.model.bean.AboutBean;
import com.yjb.mvp.presenter.user.RegistPresenter;
import com.yjb.ui.main.MainActivity;
import com.yjb.ui.sys.PhoneCodeActivity;
import com.yjb.ui.web.HuoHuoWebViewActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by kennysun on 2019/8/28.
 */

public class RegistActivity extends BaseLoadActivity implements RegistContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.tvPhoneCode)
    TextView tvPhoneCode;
    @BindView(R.id.etCode)
    EditText etCode;
    @BindView(R.id.sendSmsCode)
    AppCompatButton sendSmsCode;
    @BindView(R.id.cbXieyi)
    CheckBox cbXieyi;
    @BindView(R.id.tvRegistXieyi)
    TextView tvRegistXieyi;
    @BindView(R.id.acbBtn)
    AppCompatButton acbBtn;
    @BindView(R.id.multipleStatusView)
    MultipleStatusView multipleStatusView;

    private final int CHOOSE_PHONE_CODE = 0x0011;
    private String phoneCode = "86";//国家代码，默认是86
    private String phone;//手机号
    private CountDownTimerUtil countDownTimerUtil;
    private String webUrl;
    private RegistPresenter registPresenter;

    @Override
    public void retry() {

    }

    @Override
    public MultipleStatusView getMultipleStatusView() {
        return multipleStatusView;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_regist;
    }

    @Override
    public void initViewAndData() {
        super.initViewAndData();
        setToolbarConfig(toolbar, R.string.regist_phone);
        registPresenter = new RegistPresenter();
        registPresenter.attachView(this, this);
        phone = getIntent().getStringExtra(HuoHuoConstants.PHONE);
        if (!TextUtils.isEmpty(phone))
            etPhone.setText(phone);

        OnTextChangeListener onTextChangeListener = new OnTextChangeListener() {
            @Override
            public void afterTextChanged(Editable s) {
                changeRegistBtnState();
            }
        };
        etPhone.addTextChangedListener(onTextChangeListener);
        etCode.addTextChangedListener(onTextChangeListener);

        //请求接口获取服务协议
        registPresenter.getAbout();
    }

    private void changeRegistBtnState() {
        String s1 = etPhone.getText().toString();
        String s2 = etCode.getText().toString();
        boolean checked = cbXieyi.isChecked();
        if (!TextUtils.isEmpty(s1) && !TextUtils.isEmpty(s2) && checked) {
            acbBtn.setEnabled(true);
        } else {
            acbBtn.setEnabled(false);
        }
    }


    @OnClick({R.id.tvPhoneCode, R.id.sendSmsCode, R.id.tvRegistXieyi, R.id.acbBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvPhoneCode:
                Intent intentPCode = new Intent();
                intentPCode.setClass(this, PhoneCodeActivity.class);
                startActivityForResult(intentPCode, CHOOSE_PHONE_CODE);
                break;
            case R.id.sendSmsCode:
                phone = etPhone.getText().toString();
                registPresenter.getRegistCode(phoneCode, phone);
                break;
            case R.id.tvRegistXieyi:
                Intent intentWeb = new Intent(this, HuoHuoWebViewActivity.class);
                intentWeb.putExtra(Constants.WEBTITLE, getString(R.string.btw_server_xieyi));
                intentWeb.putExtra(Constants.WEBURL, "" + webUrl);
                intentWeb.putExtra(Constants.WEBCLOSE, false);
                startActivity(intentWeb);
                break;
            case R.id.acbBtn:
                String s = etPhone.getText().toString();//判断发送验证码后手机号是否变化
                if (!s.equals(phone)) {
                    ToastUtil.show(this, R.string.change_phone_need_sendsms);
                    return;
                }
                String code = etCode.getText().toString();
                registPresenter.regist(phoneCode, phone, code);
                break;
        }
    }

    @Override
    public void getAbout(AboutBean aboutData) {
        webUrl = aboutData.getProtocolUrl();
    }

    @Override
    public void getRegistCodeSuccess() {
        ToastUtil.show(this, R.string.code_send_success);
        countDownTimerUtil = new CountDownTimerUtil(sendSmsCode, HuoHuoConstants.CUTDOWN_TIME, 1000);
        countDownTimerUtil.start();
    }

    @Override
    public void registSuccess() {
        ToastUtil.show(this, R.string.regist_success);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == CHOOSE_PHONE_CODE) {//选择国家代码回来
                phoneCode = data.getStringExtra(HuoHuoConstants.COUNTRY_CODE);
                tvPhoneCode.setText("+" + phoneCode);
                etCode.setText("");//更改国家代码后重置验证码状态
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimerUtil != null) {
            countDownTimerUtil.cancel();
        }
    }

}
