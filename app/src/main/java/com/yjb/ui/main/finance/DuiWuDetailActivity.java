package com.yjb.ui.main.finance;

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
import com.yjb.R;
import com.yjb.dao.table.YiFcDetail;
import com.yjb.mvp.model.bean.YjTeam;
import com.yjb.ui.adapter.DuiWuAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DuiWuDetailActivity extends BaseLoadActivity {


    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvYiFcTitle)
    TextView tvYiFcTitle;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    DuiWuAdapter duiWuAdapter;
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
        YjTeam.ListBean bean = (YjTeam.ListBean) intent.getSerializableExtra("TITLE");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("队伍介绍");
        tvRight.setTextColor(getResources().getColor(R.color.colorAccent));
        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(DuiWuDetailActivity.this, TeamIntroActivity.class);
                in.putExtra("CONTENT", bean.getContent());
                startActivity(in);
            }
        });
        tvYiFcTitle.setText(bean.getSlogan());

        duiWuAdapter = new DuiWuAdapter(R.layout.item_yi_fc_detail, bean.getYjSet());
        recyclerview.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false));
        recyclerview.setAdapter(duiWuAdapter);
        duiWuAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {


            }
        });
    }

}
