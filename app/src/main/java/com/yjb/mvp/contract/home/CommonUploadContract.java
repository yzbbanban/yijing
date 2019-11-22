package com.yjb.mvp.contract.home;


import com.dian.commonlib.base.IPresenter;
import com.dian.commonlib.base.IView;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by kennysun on 2019/8/7.
 */
public interface CommonUploadContract {
    interface View extends IView {
        void getUpload(String o);
    }

    interface Presenter extends IPresenter<View> {
        void getList(RequestBody token, MultipartBody.Part file);
    }
}
