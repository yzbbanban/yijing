package com.huohuo.mvp.contract.user;

import com.dian.commonlib.base.IPresenter;
import com.dian.commonlib.base.IView;
import com.dian.commonlib.net.HttpResult;
import com.huohuo.mvp.model.bean.JiYanData;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by kennysun on 2019/8/28.
 */

public interface InputLoginCodeContract {

    interface View extends IView {
        void loginSuccess();

        void geeTest();
        void codeError();

        void jiYanApi1(JiYanData data);

        void jiYanApi2();
    }

    interface Presenter extends IPresenter<View> {
        void login(String phoneCode,String account, String code);

        void jiYanApi1();

        void jiYanApi2(String challenge, String validate, String userName, String countryCode, RequestBody seccode);

    }
}
