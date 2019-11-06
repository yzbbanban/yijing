package com.dian.commonlib.base;

import android.content.Context;

import com.dian.commonlib.app.App;
import com.dian.commonlib.net.DataManager;
import com.dian.commonlib.net.HostInterceptor;
import com.dian.commonlib.utils.SchedulerUtil;


import io.reactivex.Observable;

/**
 * Created by kennysun on 2019/8/7.
 */

public abstract class BasePresenter<T extends DataManager, V extends IView> implements IPresenter<V> {
    protected T dataManager;
    protected HostInterceptor hostInterceptor;


    protected abstract T initDataManager();

    public BasePresenter() {
        this.hostInterceptor = HostInterceptor.getHostInterceptor(App.mApp);
        dataManager = initDataManager();
    }

    private V mMvpView;
    protected Context mContext;

    @Override
    public void attachView(V mvpView, Context context) {
        mMvpView = mvpView;
        mContext = context;
    }

    @Override
    public V getMvpView() {
        return mMvpView;
    }


    /**
     * 耗时操作
     *
     * @param observable
     * @param showLoding
     * @param <T>
     * @return
     */
    public <T> Observable<T> doRequestToMain(Observable<T> observable, boolean showLoding) {
        getMvpView().showLoading(showLoding);
        return observable.compose(SchedulerUtil.ioToMain());
    }

    public <T> Observable<T> doRequestToMain(Observable<T> observable) {
        return doRequestToMain(observable, true);
    }
}
