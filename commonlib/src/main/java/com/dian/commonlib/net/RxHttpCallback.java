package com.dian.commonlib.net;

import com.dian.commonlib.base.IPresenter;
import com.dian.commonlib.base.IView;
import com.dian.commonlib.net.exception.ApiException;
import com.dian.commonlib.net.exception.ErrorStatus;
import com.dian.commonlib.net.exception.ExceptionHandle;

import java.lang.ref.WeakReference;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by kennysun on 2019/8/7.
 */

abstract public class RxHttpCallback<T> extends DisposableObserver<HttpResult<T>> {
    WeakReference<IPresenter<? extends IView>> mPresenter;

    public RxHttpCallback(IPresenter<? extends IView> presenter) {
        mPresenter = new WeakReference<>(presenter);
    }

    public abstract void onData(T data);

    public void onError(Object msg, int code) {
        mPresenter.get().getMvpView().onError(msg, code);
    }

    @Override
    public void onNext(HttpResult<T> tHttpResult) {
        if (tHttpResult.getCode() == ErrorStatus.SUCCESS) {
            onData(tHttpResult.getData());
        } else {
            ApiException e = new ApiException(tHttpResult.getMessage(), tHttpResult.getCode());
            onError(ExceptionHandle.handleException(e), ExceptionHandle.errorCode);
        }
    }

    @Override
    public void onError(Throwable e) {
        onError(ExceptionHandle.handleException(e), ExceptionHandle.errorCode);
    }

    @Override
    public void onComplete() {
        mPresenter.get().getMvpView().onComplete();
    }
}
