package com.huohuo.ui.main.finance;

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
import com.huohuo.ui.main.activi.OutSideFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class ScoreActivity extends BaseLoadActivity {

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
        tvTitle.setText("排行榜");
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
    public int getLayoutId() {
        return R.layout.activity_score;
    }


    @Override
    public void retry() {

    }

    @Override
    public MultipleStatusView getMultipleStatusView() {
        return null;
    }

    private void initData() {
        ScoreFragment scoreFragment = new ScoreFragment();
        Bundle bundleMsg = new Bundle();
        bundleMsg.putString("title", "月榜");
        scoreFragment.setArguments(bundleMsg);

        ScoreFragment scoreFragment2 = new ScoreFragment();
        Bundle bundleMsg2 = new Bundle();
        bundleMsg2.putString("title", "年榜");
        scoreFragment2.setArguments(bundleMsg2);

        ArrayList<BaseFragment> fragments = new ArrayList<>();
        fragments.add(scoreFragment);
        fragments.add(scoreFragment2);
        ArrayList<String> titles = new ArrayList<>();
        titles.add("月榜");
        titles.add("年榜");

        // set adapter
        adapter = new BaseFragmentAdapter(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
