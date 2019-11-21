package com.yjb.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;

import com.dian.commonlib.base.BaseFragment;

import java.util.ArrayList;

public class MyPagerAdapter extends FragmentPagerAdapter {
    ArrayList<BaseFragment> mFragmentList;
    ArrayList<String> mTitles;

    public MyPagerAdapter(FragmentManager fragmentManager, ArrayList<BaseFragment> fragmentList, ArrayList<String> titles) {
        super(fragmentManager);
        this.mFragmentList = fragmentList;
        this.mTitles = titles;
        setFragments(fragmentManager, fragmentList);
    }

    @Override
    public Fragment getItem(int i) {
        return mFragmentList.get(i);
    }

    @Override
    public int getCount() {
        return mFragmentList == null ? 0 : mFragmentList.size();
    }

    public void setFragments(FragmentManager fragmentManager, ArrayList<BaseFragment> fragmentList) {
        if (fragmentList != null && fragmentList.size() > 0) {
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            for (BaseFragment fragment : fragmentList) {
                beginTransaction.remove(fragment);
            }
            beginTransaction.commitNowAllowingStateLoss();
            fragmentManager.executePendingTransactions();
            this.mFragmentList = fragmentList;
            notifyDataSetChanged();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
