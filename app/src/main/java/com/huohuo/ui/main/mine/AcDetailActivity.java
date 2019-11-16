package com.huohuo.ui.main.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dian.commonlib.base.BaseLoadActivity;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.huohuo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AcDetailActivity extends BaseLoadActivity {

    private static final String TAG = "AcDetailActivity";
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.btRight)
    Button btRight;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tcAcDetailTitle)
    TextView tcAcDetailTitle;
    @BindView(R.id.tcAcDetailIntroduce)
    TextView tcAcDetailIntroduce;
    @BindView(R.id.tcAcDetailType)
    TextView tcAcDetailType;
    @BindView(R.id.tcAcDetailCount)
    TextView tcAcDetailCount;
    @BindView(R.id.tcAcDetailTime)
    TextView tcAcDetailTime;
    @BindView(R.id.tcAcDetailAddress)
    TextView tcAcDetailAddress;
    @BindView(R.id.tcAcDetailOwner)
    TextView tcAcDetailOwner;
    @BindView(R.id.tcAcDetailPhone)
    TextView tcAcDetailPhone;
    @BindView(R.id.tcAcDetailRemark)
    TextView tcAcDetailRemark;

    private Integer type;

    @Override
    public void initViewAndData() {
        super.initViewAndData();
        tvTitle.setText("活动详情");
        ivLeft.setVisibility(View.VISIBLE);
        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        type = intent.getExtras().getInt("type", 1);
        Log.i(TAG, "onNewIntent: " + type);
    }

    @Override
    public void retry() {

    }

    @Override
    public MultipleStatusView getMultipleStatusView() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_ac_detail;
    }
}
