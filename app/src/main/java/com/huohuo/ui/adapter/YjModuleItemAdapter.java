package com.huohuo.ui.adapter;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseMyViewHolder;
import com.dian.commonlib.glide.GlideEngine;
import com.huohuo.R;
import com.huohuo.mvp.model.bean.ModuleItemBean;

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
        TextView tv = helper.getView(R.id.tvModuleOrder);
        tv.setVisibility(View.GONE);
        TextView textView = helper.getView(R.id.tvModuleText);
        textView.setVisibility(View.GONE);
        int index = item.getIndex() == null ? 4 : item.getIndex();
        helper.setText(R.id.tvModuleOrder, "" + index);
        switch (index) {
            case 1:
                helper.setImageResource(R.id.ivModuleIcon, R.drawable.ls);
                break;
            case 2:
                helper.setImageResource(R.id.ivModuleIcon, R.drawable.fx);
                break;
            case 3:
                helper.setImageResource(R.id.ivModuleIcon, R.drawable.bg);
                break;
            default:
                helper.setBackgroundRes(R.id.ivModuleIcon, R.drawable.orderother);
                break;
        }
    }
}
