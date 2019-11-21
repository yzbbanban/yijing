package com.yjb.ui.dialog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.dian.commonlib.base.BaseDialog;
import com.dian.commonlib.utils.ToastUtil;
import com.yjb.R;

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

    private Integer id;

    private Context context;

    public MyPayDialog setPayId(String payInfo, Integer id) {
        this.payInfo = payInfo;
        this.id = id;
        return this;
    }

    public MyPayDialog(Context context) {
        super(context);
        this.context = context;
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
                ToastUtil.show(context, "支付了");
                dismiss();
                break;
            case R.id.tvPayCancel:
                ToastUtil.show(context, "取消支付");
                dismiss();
                break;
        }

    }
}
