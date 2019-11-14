package com.huohuo.ui.main.finance;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dian.commonlib.base.BaseLoadActivity;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.huohuo.R;

import butterknife.BindView;

public class TeamIntroActivity extends BaseLoadActivity {


    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvDuiWu)
    TextView tvDuiWu;

    @Override
    public int getLayoutId() {
        return R.layout.activity_team_intro;
    }


    @Override
    public void retry() {

    }

    @Override
    public MultipleStatusView getMultipleStatusView() {
        return null;
    }

    @Override
    public void initViewAndData() {
        super.initViewAndData();
        tvTitle.setText("队伍介绍");
        ivLeft.setVisibility(View.VISIBLE);
        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tvDuiWu.setText("邻里守望\n 邻里守望队由社区的网格长、楼栋长、热心居民等组成，主要围绕社区的平安和谐环境打造，" +
                "开展治安巡逻防范、商铺隐患检查、安全防范宣传，以及入户信息采集等工作，通过警网融合，建立起由社区 1 名警务助理");
    }
}
