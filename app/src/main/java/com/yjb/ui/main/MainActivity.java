package com.yjb.ui.main;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;

import com.dian.commonlib.base.BaseFragment;
import com.dian.commonlib.base.BaseFragmentAdapter;
import com.dian.commonlib.base.BaseLoadActivity;
import com.dian.commonlib.broadcastreceiver.NetWorkStateReceiver;
import com.dian.commonlib.utils.ToastUtil;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.dian.commonlib.utils.widget.NotScrollViewPager;
import com.yjb.R;
import com.yjb.app.HuoHuoApp;
import com.yjb.ui.main.finance.FinanceFragmrnt;
import com.yjb.ui.main.mine.MineFragmrnt;
import com.yjb.ui.main.msg.MsgFragmrnt;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.lang.reflect.Method;
import java.util.ArrayList;

import butterknife.BindView;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

public class MainActivity extends BaseLoadActivity {
    @BindView(R.id.viewPager)
    NotScrollViewPager viewPager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.bottomNavigationViewEx)
    BottomNavigationViewEx bottomNavigationViewEx;

    String TAG = "MainActivity";
    NetWorkStateReceiver netWorkStateReceiver;
    BaseFragmentAdapter adapter;
    Badge badgeMsgNum;
    @BindView(R.id.tvTitle)
    TextView tvTitle;

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

        viewPager.setOffscreenPageLimit(3);
        bottomNavigationViewEx.enableAnimation(false);//设置切换时文字图标不变大
        bottomNavigationViewEx.setLabelVisibilityMode(1);//设置文字一直可见
        bottomNavigationViewEx.setItemIconTintList(null);

//        badgeMsgNum = addBadgeAt(2);
//        badgeMsgNum.setBadgeNumber(3);

        initData();
    }


    public void updateToolBar(int title) {
        tvTitle.setText(title);
        toolbar.setBackgroundColor(getResources().getColor(R.color.bctm));
        tvTitle.setTextColor(getResources().getColor(R.color.white));
    }

    public void updateToolBarColor(int color) {
        toolbar.setBackgroundColor(getResources().getColor(color));
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


        FinanceFragmrnt financeFragment = new FinanceFragmrnt();
        Bundle bundleFind = new Bundle();
        bundleFind.putString("title", getString(R.string.home));
        financeFragment.setArguments(bundleFind);

        MsgFragmrnt msgFragment = new MsgFragmrnt();
        Bundle bundleMsg = new Bundle();
        bundleMsg.putString("title", "洋河义警");
        msgFragment.setArguments(bundleMsg);

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
