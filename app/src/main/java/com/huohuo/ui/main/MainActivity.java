package com.huohuo.ui.main;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.dian.commonlib.base.BaseFragment;
import com.dian.commonlib.base.BaseFragmentAdapter;
import com.dian.commonlib.base.BaseLoadActivity;
import com.dian.commonlib.broadcastreceiver.NetWorkStateReceiver;
import com.dian.commonlib.utils.LogUtil;
import com.dian.commonlib.utils.ToastUtil;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.dian.commonlib.utils.widget.NotScrollViewPager;
import com.huohuo.app.HuoHuoApp;
import com.huohuo.dao.table.Friend;
import com.huohuo.mvp.contract.main.MainContract;
import com.huohuo.mvp.model.bean.PhoneInfoBean;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.huohuo.R;
import com.huohuo.app.HuoHuoConstants;
import com.huohuo.im.SocketService;
import com.huohuo.mvp.presenter.main.MainPresenter;
import com.huohuo.ui.main.finance.FinanceFragmrnt;
import com.huohuo.ui.main.home.HomeFragmrnt;
import com.huohuo.ui.main.mine.MineFragmrnt;
import com.huohuo.ui.main.msg.MsgFragmrnt;

import java.lang.reflect.Method;
import java.util.ArrayList;


import butterknife.BindView;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

public class MainActivity extends BaseLoadActivity implements MainContract.View {
    @BindView(R.id.viewPager)
    NotScrollViewPager viewPager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.bottomNavigationViewEx)
    BottomNavigationViewEx bottomNavigationViewEx;

    String TAG = "MainActivity";
    NetWorkStateReceiver netWorkStateReceiver;
    MainPresenter mainPresenter;
    BaseFragmentAdapter adapter;
    Badge badgeMsgNum;

    @Override
    public void retry() {

    }

    @Override
    public MultipleStatusView getMultipleStatusView() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViewAndData() {
        super.initViewAndData();
        HuoHuoApp.mApp.removeOtherActivity(MainActivity.this);
        mainPresenter = new MainPresenter();
        mainPresenter.attachView(this, this);
        //获取用户信息
        mainPresenter.getUser();
        //更新本地msg相关信息（群聊，好友）
        mainPresenter.updateGroupAndFriendInfo();

        viewPager.setOffscreenPageLimit(3);
        bottomNavigationViewEx.enableAnimation(false);//设置切换时文字图标不变大
        bottomNavigationViewEx.setLabelVisibilityMode(1);//设置文字一直可见
        bottomNavigationViewEx.setItemIconTintList(null);

        badgeMsgNum = addBadgeAt(2);
        badgeMsgNum.setBadgeNumber(3);

        initData();
    }


    public void updateToolBar(int title) {
        setToolbarConfig(toolbar, title, -1);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (menu != null) {
            if (menu.getClass() == MenuBuilder.class) {
                try {
                    //设置menu显示图标
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
//                    Out.print(getClass().getSimpleName() + "onMenuOpened...unable to set icons for overflow menu" + e);
                }
            }
        }
        return super.onPrepareOptionsMenu(menu);
    }


    private Badge addBadgeAt(int position) {
        Badge badge = new QBadgeView(this)
                .setBadgeBackgroundColor(getResources().getColor(R.color.colorAccent))
                .setGravityOffset(28f, 1f, true)
                .setBadgeTextSize(9, true)
                .bindTarget(bottomNavigationViewEx.getBottomNavigationItemView(position))
                .setOnDragStateChangedListener((dragState, badge1, targetView) -> {
                    if (Badge.OnDragStateChangedListener.STATE_SUCCEED == dragState) {
                        ToastUtil.show(MainActivity.this, "移除了");
                    }
                });

        return badge;
    }

    /**
     * create fragments
     */
    private void initData() {

        MsgFragmrnt msgFragment = new MsgFragmrnt();
        Bundle bundleMsg = new Bundle();
        bundleMsg.putString("title", getString(R.string.home));
        msgFragment.setArguments(bundleMsg);

        FinanceFragmrnt financeFragment = new FinanceFragmrnt();
        Bundle bundleFind = new Bundle();
        bundleFind.putString("title", "义警新闻");
        financeFragment.setArguments(bundleFind);

        MineFragmrnt mineFragment = new MineFragmrnt();
        Bundle bundleMine = new Bundle();
        bundleMine.putString("title", getString(R.string.mine));
        mineFragment.setArguments(bundleMine);

        ArrayList<BaseFragment> fragments = new ArrayList<>();
        fragments.add(financeFragment);
        fragments.add(msgFragment);
        fragments.add(mineFragment);
        ArrayList<String> titles = new ArrayList<>();
        titles.add("洋河义警");
        titles.add("新闻");
        titles.add("个人中心");

        // set adapter
        adapter = new BaseFragmentAdapter(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(adapter);

        // binding with ViewPager
        bottomNavigationViewEx.setupWithViewPager(viewPager);
    }


    /**
     * 用户信息请求成功
     */
    @Override
    public void getUserSuccess() {
        //开启socket
        Intent intent = new Intent(this, SocketService.class);
        intent.putExtra(HuoHuoConstants.SOCKET_TYPE, HuoHuoConstants.SOCKET_INIT);
        startService(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (netWorkStateReceiver == null) netWorkStateReceiver = new NetWorkStateReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netWorkStateReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(netWorkStateReceiver);
    }
}
