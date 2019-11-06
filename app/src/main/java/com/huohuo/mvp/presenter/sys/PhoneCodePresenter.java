package com.huohuo.mvp.presenter.sys;

import com.dian.commonlib.net.HttpResult;
import com.dian.commonlib.net.RxHttpCallback;
import com.dian.commonlib.net.exception.ApiException;
import com.dian.commonlib.net.exception.ErrorStatus;
import com.dian.commonlib.net.exception.ExceptionHandle;
import com.dian.commonlib.utils.SchedulerUtil;
import com.huohuo.base.HuoHuoBasePresenter;
import com.huohuo.mvp.contract.sys.PhoneCodeContract;
import com.huohuo.mvp.model.bean.CountryCodeBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


/**
 * Created by kennysun on 2019/8/29.
 */

public class PhoneCodePresenter extends HuoHuoBasePresenter<PhoneCodeContract.View> implements PhoneCodeContract.Presenter {
    @Override
    public void getCountryCode() {
        doRequestToMain(dataManager.getCountryCode())
                .compose(SchedulerUtil.ioToMain())
                .subscribeWith(new RxHttpCallback<List<CountryCodeBean>>(this) {
                    @Override
                    public void onData(List<CountryCodeBean> data) {
                        ArrayList<String> customLetters = new ArrayList<>();
                        HashMap<String, Integer> letters = new HashMap<>();
                        //找出首字母
                        for (CountryCodeBean city : data) {
                            if ("1".equals(city.getCnFirstLetter())) {
                                city.setCnFirstLetter("☆");
                            }
                        }
                        //排序
                        Collections.sort(data);
                        int position = 0;
                        for (CountryCodeBean city : data) {
                            if ("1".equals(city.getCnFirstLetter())) {
                                city.setCnFirstLetter("☆");
                            }
                            String letter = city.getCnFirstLetter();
                            //如果没有这个key则加入并把位置也加入
                            if (!letters.containsKey(letter)) {
                                letters.put(letter, position);
                                customLetters.add(letter);
                            }
                            position++;
                        }
                        getMvpView().getCountryCode(customLetters, letters, data);
                    }
                });
    }
}
