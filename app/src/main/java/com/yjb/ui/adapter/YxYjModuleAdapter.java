package com.yjb.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseMyViewHolder;
import com.yjb.R;
import com.yjb.mvp.model.bean.ModuleBean;
import com.yjb.mvp.model.bean.YjTeam;

import java.util.List;

/**
 * Created by kennysun on 2019/9/2.
 */

public class YxYjModuleAdapter extends BaseQuickAdapter<YjTeam.ListBean, BaseMyViewHolder> {

    private Context context;

    public YxYjModuleAdapter(int layoutResId, @Nullable List<YjTeam.ListBean> data) {
        super(layoutResId, data);
    }

    public YxYjModuleAdapter(int layoutResId, @Nullable List<YjTeam.ListBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseMyViewHolder helper, YjTeam.ListBean item) {
        RecyclerView recyclerview = helper.getView(R.id.rvYiFc);
        helper.setText(R.id.tvYiFcTitle, item.getTeamname());
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        YxYjModuleItemAdapter yxYjModuleItemAdapter;
        yxYjModuleItemAdapter = new YxYjModuleItemAdapter(R.layout.item_yxyj_module_type, item.getYjSet());
        recyclerview.setAdapter(yxYjModuleItemAdapter);
    }
}
