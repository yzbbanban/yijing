package com.yjb.ui.user;

import com.dian.commonlib.base.BaseLoadActivity;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.yjb.R;

/**
 * Created by kennysun on 2019/8/29.
 */

public class MobileCodeActivty extends BaseLoadActivity {
    @Override
    public void retry() {

    }

    @Override
    public MultipleStatusView getMultipleStatusView() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_mobile_code;
    }
}
