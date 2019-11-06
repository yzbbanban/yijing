package com.huohuo.mvp.contract.sys;

import com.dian.commonlib.base.IPresenter;
import com.dian.commonlib.base.IView;
import com.huohuo.mvp.model.bean.CountryCodeBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kennysun on 2019/8/29.
 */

public interface PhoneCodeContract {
    interface View extends IView {
        void getCountryCode(ArrayList<String> customLetters, HashMap<String, Integer> letters, List<CountryCodeBean> countryCodes);

    }

    interface Presenter extends IPresenter<View> {
        void getCountryCode();
    }
}
