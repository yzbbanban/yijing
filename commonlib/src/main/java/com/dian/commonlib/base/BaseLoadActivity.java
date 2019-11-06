package com.dian.commonlib.base;

import com.dian.commonlib.net.exception.ErrorStatus;
import com.dian.commonlib.utils.ToastUtil;
import com.dian.commonlib.utils.widget.MultipleStatusView;

/**
 * Created by kennysun on 2019/8/7.
 */

abstract public class BaseLoadActivity extends BaseActivity implements IView {
    protected MultipleStatusView mMultipleStatusView;

    /**
     * 重新加载
     */
    public abstract void retry();

    public abstract MultipleStatusView getMultipleStatusView();

    /**
     * 是否允许展示错误界面
     */
    protected boolean canShowError() {
        return false;
    }

    @Override
    public void initViewAndData() {
        mMultipleStatusView = getMultipleStatusView();
        if (mMultipleStatusView != null)
            mMultipleStatusView.setOnRetryClickListener(v -> retry());
    }

    /**
     * showloding
     */
    @Override
    public void showLoading(boolean show) {
        if (show) {
            if (mMultipleStatusView != null)
                mMultipleStatusView.showLoading();
        }
    }

    /**
     * dismissloding
     */
    @Override
    public void onComplete() {
        if (mMultipleStatusView != null)
            mMultipleStatusView.showContent();
    }

    /**
     * 接口请求错误
     */
    @Override
    public void onError(Object msg, int code) {
        if (canShowError()) {
            if (code == ErrorStatus.NETWORK_ERROR) {
                if (mMultipleStatusView != null)
                    mMultipleStatusView.showNoNetwork();
            } else {
                if (mMultipleStatusView != null)
                    mMultipleStatusView.showError();
            }
        }else{
            if (mMultipleStatusView != null)
                mMultipleStatusView.showContent();
        }
        //打印吐司
        ToastUtil.show(this, msg);
        handHttpCode(code);
    }
}
