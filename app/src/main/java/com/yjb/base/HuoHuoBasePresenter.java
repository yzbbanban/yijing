package com.yjb.base;

import com.dian.commonlib.base.BasePresenter;
import com.dian.commonlib.base.IView;
import com.dian.commonlib.net.HttpResult;
import com.dian.commonlib.net.exception.ApiException;
import com.dian.commonlib.net.exception.ExceptionHandle;
import com.yjb.BuildConfig;
import com.yjb.mvp.model.HuoHuoDataManager;
import com.yjb.net.HuoHuoApi;

/**
 * Created by kennysun on 2019/8/27.
 */

public class HuoHuoBasePresenter<V extends IView> extends BasePresenter<HuoHuoDataManager, V> {
    @Override
    protected HuoHuoDataManager initDataManager() {
        return HuoHuoDataManager.getHuoHuoDataManager(BuildConfig.API_HOST, HuoHuoApi.class);
    }
    protected void handApiException(HttpResult<?> it) {
        ApiException e = new ApiException(it.getMsg(), it.getCode());
        handException(e, it.getCode());
    }

    protected void handException(Throwable e, int code) {
        getMvpView().onError(ExceptionHandle.handleException(e), code);
    }
}
