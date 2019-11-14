package com.huohuo.ui.main.finance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseLoadActivity;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.huohuo.R;
import com.huohuo.dao.table.YiFcDetail;
import com.huohuo.ui.adapter.YiFcDetailAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YiFcDetailActivity extends BaseLoadActivity {


    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvYiFcTitle)
    TextView tvYiFcTitle;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    YiFcDetailAdapter yiFcDetailAdapter;
    @BindView(R.id.tvRight)
    TextView tvRight;


    @Override
    public void retry() {

    }

    @Override
    public MultipleStatusView getMultipleStatusView() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_yi_fc_detail;
    }

    @Override
    public void initViewAndData() {
        super.initViewAndData();
        ivLeft.setVisibility(View.VISIBLE);
        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent intent = getIntent();
        String title = intent.getStringExtra("TITLE");
        tvYiFcTitle.setText(title);
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("队伍介绍");
        tvRight.setTextColor(getResources().getColor(R.color.colorAccent));
        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(YiFcDetailActivity.this, TeamIntroActivity.class);
                startActivity(in);
            }
        });


        List<YiFcDetail> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            YiFcDetail yiFcDetail = new YiFcDetail();
            yiFcDetail.setId(i + 1);
            yiFcDetail.setTitle("这是啥" + i);
            yiFcDetail.setUrl("");
            list.add(yiFcDetail);
        }
        yiFcDetailAdapter = new YiFcDetailAdapter(R.layout.item_yi_fc_detail, list, 1);
        recyclerview.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false));
        recyclerview.setAdapter(yiFcDetailAdapter);
        yiFcDetailAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {


            }
        });
    }

}
