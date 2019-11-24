package com.yjb.ui.dialog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.dian.commonlib.base.BaseDialog;
import com.dian.commonlib.utils.AppUtil;
import com.dian.commonlib.utils.ToastUtil;
import com.yjb.R;
import com.yjb.mvp.presenter.home.ExchangePayPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by kennysun on 2019/9/3.
 */

public class MyPayDialog extends BaseDialog {

    @BindView(R.id.tvPayTitle)
    TextView tvPayTitle;
    @BindView(R.id.tvPay)
    TextView tvPay;
    @BindView(R.id.tvPayCancel)
    TextView tvPayCancel;

    private String payInfo;
    private String score;

    private Integer id;

    private Context context;

    private ExchangePayPresenter exchangePayPresenter;

    public MyPayDialog setPayId(String payInfo, Integer id, String score) {
        this.payInfo = payInfo;
        this.id = id;
        this.score = score;
        return this;
    }

    public MyPayDialog(Context context) {
        super(context);
        this.context = context;
    }

    public MyPayDialog(Context context, ExchangePayPresenter exchangePayPresenter) {
        super(context);
        this.context = context;
        this.exchangePayPresenter = exchangePayPresenter;
    }

    @Override
    public int getView() {
        return R.layout.dialog_my_pay;
    }

    @Override
    public void initViewAndData() {
        tvPayTitle.setText(payInfo);

    }

    @OnClick({R.id.tvPay, R.id.tvPayCancel})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.tvPay:
                ToastUtil.show(context, "兑换中");
                exchangePayPresenter.getList(AppUtil.getToken(), AppUtil.getUser(), "" + id, score);
                dismiss();
                break;
            case R.id.tvPayCancel:
                ToastUtil.show(context, "取消支付");
                dismiss();
                break;
        }

    }
}
