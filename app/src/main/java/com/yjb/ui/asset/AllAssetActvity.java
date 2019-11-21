package com.yjb.ui.asset;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.dian.commonlib.base.BaseActivity;
import com.dian.commonlib.base.BaseFragment;
import com.dian.commonlib.base.BaseFragmentAdapter;
import com.yjb.R;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by kennysun on 2019/9/5.
 */

public class AllAssetActvity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private ArrayList<String> titles;
    private BaseFragmentAdapter baseFragmentAdapter;
    private ArrayList<BaseFragment> fragments;

    @Override
    public int getLayoutId() {
        return R.layout.activity_all_asset;
    }

    @Override
    public void initViewAndData() {
        setToolbarConfig(toolbar, R.string.all_asset);
        titles = new ArrayList<>();
        fragments = new ArrayList<>();
        titles.add(getResources().getString(R.string.yun_wallet));
        titles.add(getResources().getString(R.string.licai));
        fragments.add(YunWalletFragment.getYunWalletFragment());
        fragments.add(LicaiFragment.getLicaiFragment());
        for (String title : titles) {
            tabLayout.addTab(tabLayout.newTab().setText(title));
        }
        baseFragmentAdapter = new BaseFragmentAdapter(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(baseFragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_all_asset_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bill:
                //todo 账单
                break;
        }
        return true;
    }
}
