package com.huohuo.ui.sys;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dian.commonlib.glide.GlideEngine;
import com.dian.commonlib.utils.BigDecimalUtil;
import com.dian.commonlib.utils.MmkvUtil;
import com.dian.commonlib.utils.RSAUtil;
import com.dian.commonlib.utils.widget.MyBottomSheetDialog;
import com.dian.commonlib.utils.widget.paydialog.PayKeyBoard;
import com.dian.commonlib.utils.widget.paydialog.PayPasswordInputView;
import com.huohuo.BuildConfig;
import com.huohuo.R;
import com.huohuo.app.HuoHuoApp;
import com.huohuo.app.HuoHuoConstants;
import com.huohuo.mvp.model.bean.User;

import org.bouncycastle.util.encoders.Base64;

import java.math.BigDecimal;

/**
 * Created by kennysun on 2019/8/29.
 */

public class PayDialogFragment extends BottomSheetDialogFragment implements PayPasswordInputView.OnPasswordCompleteCallback, PayKeyBoard.OnChangedListener, View.OnClickListener {

    private BottomSheetBehavior mBehavior;
    PayKeyBoard payKeyBoard;
    private FrameLayout viewPayDialog;
    public PayPasswordInputView passwordInputView;
    private ImageView close;
    private TextView tvDialogTitle;
    private TextView tvChangeType;//使用密码、使用指纹
    private LinearLayout llPayContent;//钱相关部分
    private LinearLayout llZhuanCoin;//转账部分
    private LinearLayout llHongbao;//红包部分
    private LinearLayout llAuth;//实名认证部分
    private TextView tvTitle;//钱相关标题
    private TextView tvCoinCount;//
    private TextView tvRmb;
    private TextView tvTarget;//转账目标（人或地址）
    private TextView tvCoinName;
    private TextView tvHint;
    private ImageView ivCoinLogo;
    private ImageView ivZhiWen;
    private View lineView;
    private HuoHuoConstants.PayType payType;

    private String title;
    private String coinCount;
    private String rate;
    private String target;
    private String coinName;
    private String coinLogo;
    PayDialogCallback payDialogCallback;
    CloseDialogCallback closeDialogCallback;

    //用户信息
    private User.UserInfo userInfo;
    private String userId;
    private String jsonValue;       //用来判断是否设置指纹支付

    public void setPayDialogCallback(PayDialogCallback payDialogCallback) {
        this.payDialogCallback = payDialogCallback;
    }

    public interface PayDialogCallback {
        void getPwd(String pwd);
    }

    //关闭支付弹窗
    public interface CloseDialogCallback {
        void closeDialog();
    }

    public void setCloseDialogCallback(CloseDialogCallback closeDialogCallback) {
        this.closeDialogCallback = closeDialogCallback;
    }

    public static PayDialogFragment newInstance(Bundle args) {
        PayDialogFragment payDialogFragment = new PayDialogFragment();
        payDialogFragment.setArguments(args);
        return payDialogFragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        setCancelable(false);
        MyBottomSheetDialog dialog = new MyBottomSheetDialog(getContext());
        View view = View.inflate(getContext(), R.layout.dialog_fragment_pay, null);
        userInfo = User.getUser().getUserInfo();
        userId = String.valueOf(userInfo.getId());
        jsonValue = MmkvUtil.decodeString(HuoHuoConstants.JSONVALUE + userId,"");

        Bundle bundle = getArguments();
        payType = (HuoHuoConstants.PayType) bundle.getSerializable(HuoHuoConstants.PAY_TYPE);
        title = bundle.getString(HuoHuoConstants.PAY_TITLE);
        coinCount = bundle.getString(HuoHuoConstants.PAY_COIN_COUNT);
        rate = bundle.getString(HuoHuoConstants.PAY_RATE);
        target = bundle.getString(HuoHuoConstants.PAY_TARGET);
        coinName = bundle.getString(HuoHuoConstants.PAY_COIN_NAME);
        coinLogo = bundle.getString(HuoHuoConstants.PAY_COIN_LOGO);

//        Toast.makeText(getContext(), "type:" + payType.name(), Toast.LENGTH_LONG).show();

        initView(view);

        initData();

        dialog.setContentView(view);
        mBehavior = BottomSheetBehavior.from((View) view.getParent());
        return dialog;
    }

    private String format;

    public void clear() {
        payKeyBoard.clear();
        passwordInputView.clear();
    }

    private void initData() {
        close.setOnClickListener(this);
        tvChangeType.setOnClickListener(this);
        payKeyBoard.setOnChangedListener(this);
        passwordInputView.setOnPasswordCompleteCallback(this);

        tvTitle.setText(title);
        if (!TextUtils.isEmpty(coinCount)) {
            BigDecimal bigDecimal = new BigDecimal(coinCount);
            BigDecimal bigDecimal1 = new BigDecimal(rate);
            BigDecimal multiply = bigDecimal.multiply(bigDecimal1);
            String string1 = getResources().getString(R.string.fabi_fuzhi_yue);
            format = string1 + BigDecimalUtil.getFaBiLable() +"："+ BigDecimalUtil.getString(multiply, "0", HuoHuoConstants.RMB_JINGDU);
        }


        switch (payType) {
            case FRIEND_TRANSFER_COIN:
            case ADDRESS_TRANSFER_COIN:
                if (payType.equals(HuoHuoConstants.PayType.AUTHENTICATION))
                    return;
                llPayContent.setVisibility(View.GONE);
                lineView.setVisibility(View.GONE);
                break;
            case IN_CQG:
            case OUT_CQG:
            case FA_HONG_BAO:
                if (payType.equals(HuoHuoConstants.PayType.AUTHENTICATION))
                    return;
                tvCoinCount.setText(coinCount);
                tvRmb.setText(format);
                GlideEngine.load(ivCoinLogo, BuildConfig.API_IMG_HOST + coinLogo);
                tvCoinName.setText(coinName);
                llPayContent.setVisibility(View.VISIBLE);
                llZhuanCoin.setVisibility(View.GONE);
                llHongbao.setVisibility(View.VISIBLE);
                llAuth.setVisibility(View.GONE);
                break;
            case AUTH:
                llPayContent.setVisibility(View.GONE);
                llZhuanCoin.setVisibility(View.GONE);
                llHongbao.setVisibility(View.GONE);
                llAuth.setVisibility(View.VISIBLE);
                tvHint.setText(R.string.auth_paypwd);
                break;
            case GAMEGO:
                llPayContent.setVisibility(View.GONE);
                llZhuanCoin.setVisibility(View.GONE);
                llHongbao.setVisibility(View.GONE);
                llAuth.setVisibility(View.VISIBLE);
                tvHint.setText(R.string.h5_pay);
                break;
            case THIRD_PAY:
                llPayContent.setVisibility(View.GONE);
                llZhuanCoin.setVisibility(View.GONE);
                llHongbao.setVisibility(View.GONE);
                llAuth.setVisibility(View.VISIBLE);
                tvHint.setText("请输入支付密码");
                break;
            case AUTHENTICATION:
                llPayContent.setVisibility(View.GONE);
                llZhuanCoin.setVisibility(View.GONE);
                llHongbao.setVisibility(View.GONE);
                llAuth.setVisibility(View.VISIBLE);
                tvHint.setText(R.string.auth_zhiwen);
                break;
        }

    }

    private void initView(View view) {
        //关闭按钮
        close = view.findViewById(R.id.close);
        viewPayDialog = view.findViewById(R.id.viewPayDialog);
        //键盘
        payKeyBoard = view.findViewById(R.id.keyBoardView);
        //密码框
        passwordInputView = view.findViewById(R.id.passwordInputView);
        tvDialogTitle = view.findViewById(R.id.tvDialogTitle);
        tvChangeType = view.findViewById(R.id.tvChangeType);
        tvTitle = view.findViewById(R.id.tvTitle);
        tvCoinCount = view.findViewById(R.id.tvCoinCount);
        tvRmb = view.findViewById(R.id.tvRmb);
        tvTarget = view.findViewById(R.id.tvTarget);
        tvCoinName = view.findViewById(R.id.tvCoinName);
        ivCoinLogo = view.findViewById(R.id.ivCoinLogo);
        llPayContent = view.findViewById(R.id.llPayContent);
        llZhuanCoin = view.findViewById(R.id.llZhuanCoin);
        llHongbao = view.findViewById(R.id.llHongbao);
        llAuth = view.findViewById(R.id.llAuth);
        tvHint = view.findViewById(R.id.tvHint);
        lineView = view.findViewById(R.id.lineView);

        ivZhiWen = view.findViewById(R.id.ivZhiWen);
        if (!TextUtils.isEmpty(jsonValue) && payType != HuoHuoConstants.PayType.AUTH && payType != HuoHuoConstants.PayType.AUTHENTICATION && payType != HuoHuoConstants.PayType.GAMEGO) {
            tvChangeType.setText(R.string.pwd_pay);
            passwordInputView.setVisibility(View.GONE);
            ivZhiWen.setVisibility(View.VISIBLE);
            payKeyBoard.setVisibility(View.INVISIBLE);
        } else {
            tvChangeType.setText("");
            passwordInputView.setVisibility(View.VISIBLE);
            ivZhiWen.setVisibility(View.GONE);
            payKeyBoard.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        //默认全屏展开
        mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    @Override
    public void onPasswordComplete(String value) {
        if (payDialogCallback != null) {
            try {
                byte[] encrypt = RSAUtil.encrypt(value.getBytes(), RSAUtil.getPublicKey(RSAUtil.PUBLICKEY));
                value = Base64.toBase64String(encrypt);
                payDialogCallback.getPwd(value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        Toast.makeText(getContext(), "您输入的密码是：" + value, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onChanged(String value) {
        if (TextUtils.isEmpty(value) || value.length() <= 6) {
            passwordInputView.setValue(value);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.close:
                dismiss();
                if (closeDialogCallback != null)
                    closeDialogCallback.closeDialog();
                break;
            case R.id.tvChangeType:
                if (!TextUtils.isEmpty(jsonValue)) {
                    if (tvChangeType.getText().equals(getString(R.string.pwd_pay))) {       //使用密码
                        tvChangeType.setText(getString(R.string.pwd_fingerprint));
                        passwordInputView.setVisibility(View.VISIBLE);
                        ivZhiWen.setVisibility(View.GONE);
                        payKeyBoard.setVisibility(View.VISIBLE);
                    } else {                                                               //使用指纹
                        tvChangeType.setText(getString(R.string.pwd_pay));
                        passwordInputView.setVisibility(View.GONE);
                        ivZhiWen.setVisibility(View.VISIBLE);
                        payKeyBoard.setVisibility(View.INVISIBLE);
                    }

                    if (onChangeTypeCallback != null)
                        onChangeTypeCallback.changeTypeCallback(String.valueOf(tvChangeType.getText()));
                }
                break;
        }
    }

    public interface OnChangeTypeCallback {
        void changeTypeCallback(String type);
    }

    public OnChangeTypeCallback onChangeTypeCallback;

    public void setOnChangeTypeCallback(OnChangeTypeCallback onChangeTypeCallback) {
        this.onChangeTypeCallback = onChangeTypeCallback;
    }
}
