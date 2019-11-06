package com.huohuo.ui.user;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dian.commonlib.base.BaseLoadActivity;
import com.dian.commonlib.lang.MultiLanguageUtil;
import com.dian.commonlib.utils.ToastUtil;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.dian.commonlib.utils.widget.OnTextChangeListener;
import com.dian.commonlib.utils.widget.TipsDialog;
import com.huohuo.R;
import com.huohuo.app.HuoHuoConstants;
import com.huohuo.mvp.contract.user.LoginContract;
import com.huohuo.mvp.presenter.user.LoginPresenter;
import com.huohuo.ui.main.MainActivity;
import com.huohuo.ui.set.LanguageActivity;
import com.huohuo.ui.sys.PhoneCodeActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by kennysun on 2019/8/8.
 */

public class LoginActivity extends BaseLoadActivity implements LoginContract.View {

    LoginPresenter loginPresenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tvSign)
    TextView tvSign;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.sendSmsCode)
    Button sendSmsCode;

    @BindView(R.id.multipleStatusView)
    MultipleStatusView multipleStatusView;
    private final int GET_PHONE_CODE = 0x0001;//获取国家代码
    private final int YANZHENGMA_LOGIN = 0x0002;//验证码登录跳转验证码输入界面
    private String phone;
    private String phoneCode = "86";//国家代码，默认86

    private String loginType;               //登录类型(FORGET_GETTURE_LOCK：忘记手势锁密码)
    private String gestureLock;
    private int selectedLanguage;//上一次选择的语言


    @Override
    public void retry() {

    }

    @Override
    public MultipleStatusView getMultipleStatusView() {
        return multipleStatusView;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }


    @Override
    public void initViewAndData() {
        toolbar.setVisibility(View.GONE);
        super.initViewAndData();
        loginPresenter = new LoginPresenter();
        loginPresenter.attachView(this, this);

        selectedLanguage = MultiLanguageUtil.getInstance().getLanguageType();

        initEdit();
        loginType = getIntent().getStringExtra(HuoHuoConstants.TYPE);
        gestureLock = getIntent().getStringExtra(HuoHuoConstants.GESTURE_LOCK);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        loginType = intent.getStringExtra(HuoHuoConstants.TYPE);
    }

    private void initEdit() {
        OnTextChangeListener onTextChangeListener = new OnTextChangeListener() {
            @Override
            public void afterTextChanged(Editable s) {
                changeAcbBtn();//改变登录按钮的点击状态
            }
        };
        etPhone.addTextChangedListener(onTextChangeListener);
    }

    private void changeAcbBtn() {
        String phone = etPhone.getText().toString().trim();

    }

    @Override
    public void getCodeSuccess() {
        ToastUtil.show(this, R.string.code_send_success);


    }

    @Override
    public void registNever() {
        new TipsDialog().showTipsDialog(this,
                R.string.not_regist_phone,
                getResources().getString(R.string.go_regist),
                "", (dialog, which) -> {
                    dialog.dismiss();
                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this, RegistActivity.class);
                    intent.putExtra(HuoHuoConstants.PHONE, phone);
                    startActivity(intent);
                }, null);
    }


    @OnClick({R.id.sendSmsCode, R.id.btnLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sendSmsCode:
                Intent intentCode = new Intent();
                intentCode.setClass(this, PhoneCodeActivity.class);
                startActivityForResult(intentCode, GET_PHONE_CODE);
                break;
            case R.id.btnLogin:
                phone = etPhone.getText().toString();
//                loginPresenter.getCode(phone);
                startActivity(new Intent(this, MainActivity.class));
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == GET_PHONE_CODE) {//返回国家代码
                phoneCode = data.getStringExtra(HuoHuoConstants.COUNTRY_CODE);
            }
        }
    }


    @Override
    public boolean onKeyDown(int keyCoder, KeyEvent event) {
        if (keyCoder == KeyEvent.KEYCODE_BACK) {
            //第三方支付拉起页面，返回时处理
            Intent intent = new Intent();
            intent.putExtra(HuoHuoConstants.TYPE, "back");
            setResult(RESULT_OK, intent);
            finish();
        }
        super.onKeyDown(keyCoder, event);
        return false;
    }

}
