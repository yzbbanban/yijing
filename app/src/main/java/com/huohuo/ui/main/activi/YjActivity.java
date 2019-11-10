package com.huohuo.ui.main.activi;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dian.commonlib.base.BaseFragment;
import com.dian.commonlib.base.BaseFragmentAdapter;
import com.dian.commonlib.base.BaseLoadActivity;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.huohuo.R;

import java.util.ArrayList;

import butterknife.BindView;

public class YjActivity extends BaseLoadActivity {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    BaseFragmentAdapter adapter;

    @Override
    public void initViewAndData() {
        super.initViewAndData();
        tvTitle.setText("义警活动");
        ivLeft.setVisibility(View.VISIBLE);
        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initData();
    }

    @Override
    public void retry() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_yj;
    }


    @Override
    public MultipleStatusView getMultipleStatusView() {
        return null;
    }

    private void initData() {
        OutSideFragment outSideFragment = new OutSideFragment();
        Bundle bundleMsg = new Bundle();
        bundleMsg.putString("title", "报名中");
        outSideFragment.setArguments(bundleMsg);

        OutSideFragment outSideFragment2 = new OutSideFragment();
        Bundle bundleMsg2 = new Bundle();
        bundleMsg2.putString("title", "进行中");
        outSideFragment2.setArguments(bundleMsg2);

        OutSideFragment outSideFragment3 = new OutSideFragment();
        Bundle bundleMsg3 = new Bundle();
        bundleMsg3.putString("title", "已结束");
        outSideFragment3.setArguments(bundleMsg3);


        ArrayList<BaseFragment> fragments = new ArrayList<>();
        fragments.add(outSideFragment);
        fragments.add(outSideFragment2);
        fragments.add(outSideFragment3);
        ArrayList<String> titles = new ArrayList<>();
        titles.add("报名中");
        titles.add("进行中");
        titles.add("已结束");

        // set adapter
        adapter = new BaseFragmentAdapter(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }


}
