package com.huohuo.ui.user;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dian.commonlib.base.BaseLoadActivity;
import com.dian.commonlib.utils.ToastUtil;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.dian.commonlib.utils.widget.OnTextChangeListener;
import com.huohuo.R;
import com.huohuo.app.HuoHuoConstants;
import com.huohuo.mvp.contract.user.InputLoginCodeContract;
import com.huohuo.mvp.contract.user.LoginContract;
import com.huohuo.mvp.model.bean.JiYanData;
import com.huohuo.mvp.presenter.user.InputLoginCodePresenter;
import com.huohuo.mvp.presenter.user.LoginPresenter;
import com.huohuo.ui.main.MainActivity;
import com.huohuo.ui.sys.PhoneCodeActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by kennysun on 2019/8/8.
 */

public class LoginActivity extends BaseLoadActivity implements InputLoginCodeContract.View {

    InputLoginCodePresenter inputLoginCodePresenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tvSign)
    TextView tvSign;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etCode)
    EditText etCode;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.sendSmsCode)
    Button sendSmsCode;


    @Override
    public void retry() {

    }

    @Override
    public MultipleStatusView getMultipleStatusView() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }


    @Override
    public void initViewAndData() {
        toolbar.setVisibility(View.GONE);
        super.initViewAndData();
        inputLoginCodePresenter = new InputLoginCodePresenter();
        inputLoginCodePresenter.attachView(this, this);
        initEdit();
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
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
    public void loginSuccess() {
        ToastUtil.show(this, R.string.login_success);
        startActivity(new Intent(this, MainActivity.class));
        finish();

    }

    @Override
    public void geeTest() {

    }

    @Override
    public void codeError() {

    }

    @Override
    public void jiYanApi1(JiYanData data) {

    }

    @Override
    public void jiYanApi2() {

    }

    @OnClick({R.id.sendSmsCode, R.id.btnLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sendSmsCode:
                Intent intentCode = new Intent();
                intentCode.setClass(this, PhoneCodeActivity.class);
                break;
            case R.id.btnLogin:
//                loginPresenter.getCode(phone);
                String phoneCode = "86";
                String phone = etPhone.getText().toString();
                String code = etCode.getText().toString();
                inputLoginCodePresenter.login(phoneCode, phone, code);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
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
