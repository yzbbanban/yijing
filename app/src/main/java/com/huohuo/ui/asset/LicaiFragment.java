package com.huohuo.ui.asset;

import com.dian.commonlib.base.BaseRefreshFragment;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.huohuo.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

/**
 * Created by kennysun on 2019/9/5.
 */

public class LicaiFragment extends BaseRefreshFragment {
    public static LicaiFragment getLicaiFragment(){
        LicaiFragment licaiFragment = new LicaiFragment();
        return licaiFragment;
    }
    @Override
    protected void retry() {

    }

    @Override
    protected MultipleStatusView getMultipleStatusView() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_licai;
    }

    @Override
    public SmartRefreshLayout getSmartRefreshView() {
        return null;
    }

    @Override
    public void refresh() {

    }

    @Override
    protected void loadMore() {

    }
}
