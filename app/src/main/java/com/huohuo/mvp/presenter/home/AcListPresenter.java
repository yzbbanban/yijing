package com.huohuo.mvp.presenter.home;

import com.dian.commonlib.net.RxHttpCallback;
import com.dian.commonlib.utils.ToastUtil;
import com.huohuo.R;
import com.huohuo.base.HuoHuoBasePresenter;
import com.huohuo.mvp.contract.home.YjAcListContract;
import com.huohuo.mvp.contract.user.LoginContract;
import com.huohuo.mvp.model.bean.ActivityList;
import com.huohuo.net.HuoHuoErrorStatus;


/**
 * Created by kennysun on 2019/8/7.
 */

public class AcListPresenter extends HuoHuoBasePresenter<YjAcListContract.View> implements YjAcListContract.Presenter {

    @Override
    public void getList(String token, String page, String size, String type) {
        if (token.isEmpty() || page.isEmpty() || size.isEmpty() || type.isEmpty()) {
            return;
        }
        doRequestToMain(dataManager.getYjAc(token, page, size, type))
                .subscribeWith(new RxHttpCallback<ActivityList>(this) {
                    @Override
                    public void onData(ActivityList data) {
                        getMvpView().getAcYjListSuccess(data);
                    }

                    @Override
                    public void onError(Object msg, int code) {
                        super.onError(msg, code);
                    }
                });
    }

}
