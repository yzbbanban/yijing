package com.huohuo.mvp.contract.sys;

import com.dian.commonlib.base.IPresenter;
import com.dian.commonlib.base.IView;
import com.dian.commonlib.net.HttpResult;
import com.huohuo.mvp.model.bean.CountryCodeBean;
import com.huohuo.mvp.model.bean.ScanBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by kennysun on 2019/8/29.
 */

public interface ScanContract {
    interface View extends IView {

    }

    interface Presenter extends IPresenter<View> {
        void getCoinAddressType(String code);

        /**
         * 转币页面扫码专用
         * 扫描二维码、或者输入一段字符串，判断字符串类型（内部币种地址、外部币种地址、其他字符串）
         *
         * @param code
         * @return
         */
        void getTransSend(String code);

        void decodingUserid(String id);
    }
}
