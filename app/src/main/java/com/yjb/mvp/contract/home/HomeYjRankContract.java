package com.yjb.mvp.contract.home;


import com.dian.commonlib.base.IPresenter;
import com.dian.commonlib.base.IView;
import com.yjb.mvp.model.bean.HomeYjRank;

import java.util.*;

/**
 * Created by kennysun on 2019/8/7.
 */
public interface HomeYjRankContract {
    interface View extends IView {
        void getRankSuccess(List<HomeYjRank> homeYjRank);
    }

    interface Presenter extends IPresenter<View> {
        void getList(String token);
    }
}
