package com.yjb.ui.main.mine;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dian.commonlib.base.BaseLoadActivity;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.yjb.R;

import butterknife.BindView;

public class AboutUsActivity extends BaseLoadActivity {


    @BindView(R.id.tv_about_us)
    TextView tv_about_us;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;

    @Override
    public int getLayoutId() {
        return R.layout.activity_about_us;
    }

    @Override
    public void initViewAndData() {
        super.initViewAndData();
        tvTitle.setText("关于我们");
        ivLeft.setVisibility(View.VISIBLE);
        ivLeft.setOnClickListener(view -> finish());
        String msg = "洋河“义警”是一支出于奉献、友爱、互助和社会责任，自愿，无偿地以自己的时间，技能等资源开展治安巡逻、防范宣传、纠纷调解、信息采集、帮扶关爱等活动的爱心群体，是一支自愿参与社会群防群治，为平安洋河创建做奉献的志愿者队伍";
        tv_about_us.setText("" + msg);
    }

    @Override
    public void retry() {

    }

    @Override
    public MultipleStatusView getMultipleStatusView() {
        return null;
    }
}
