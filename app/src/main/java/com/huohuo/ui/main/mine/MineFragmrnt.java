package com.huohuo.ui.main.mine;


import android.view.Menu;
import android.view.MenuInflater;

import com.dian.commonlib.base.BaseFragment;
import com.huohuo.R;
import com.huohuo.ui.main.MainActivity;
import com.huohuo.ui.adapter.MsgListAdapter;

/**
 * Created by kennysun on 2019/8/8.
 */

public class MineFragmrnt extends BaseFragment {
    private MainActivity mainActivity;
    private boolean isVisibleToUser;
    private boolean isLoad = false;
    private boolean isCreated = false;
    private MsgListAdapter msgListAdapter;

    @Override
    public int getLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initViewAndData() {
        isCreated = true;
        lazyLoad();
    }

    public void lazyLoad() {
        if (isVisibleToUser && isCreated) {
            mainActivity = (MainActivity) getActivity();
            mainActivity.updateToolBar(R.string.grzx);
            setHasOptionsMenu(true);
            if (!isLoad) {
                //todo 加载数据
            }
            isLoad = true;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        lazyLoad();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_mine_toolbar, menu);
    }

}
