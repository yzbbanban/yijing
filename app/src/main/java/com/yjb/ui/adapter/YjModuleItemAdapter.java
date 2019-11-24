package com.yjb.ui.adapter;

import android.support.annotation.Nullable;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseMyViewHolder;
import com.yjb.R;
import com.yjb.mvp.model.bean.ModuleItemBean;

import java.util.List;

/**
 * Created by kennysun on 2019/9/2.
 */

public class YjModuleItemAdapter extends BaseQuickAdapter<ModuleItemBean, BaseMyViewHolder> {
    public YjModuleItemAdapter(int layoutResId, @Nullable List<ModuleItemBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseMyViewHolder helper, ModuleItemBean item) {
        Log.i(TAG, "convert: " + item);
        int index = item.getIndex() == null ? 4 : item.getIndex();
        switch (index) {
            case 1:
                helper.setImageResource(R.id.ivDW, R.drawable.ls);
                break;
            case 2:
                helper.setImageResource(R.id.ivDW, R.drawable.fx);
                break;
            case 3:
                helper.setImageResource(R.id.ivDW, R.drawable.bg);
                break;
            default:
                helper.setBackgroundRes(R.id.ivDW, R.drawable.ls);
                break;
        }
    }
}
