package com.huohuo.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseMyViewHolder;
import com.huohuo.mvp.model.bean.ModuleItemBean;

import java.util.Collections;
import java.util.List;

/**
 * Created by kennysun on 2019/9/2.
 */

public class ModuleEditItemAdapter extends BaseQuickAdapter<ModuleItemBean,BaseMyViewHolder> {
    public int parentPosi;

    public void setParentPosi(int parentPosi) {
        this.parentPosi = parentPosi;
    }

    public ModuleEditItemAdapter(int layoutResId, @Nullable List<ModuleItemBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseMyViewHolder helper, ModuleItemBean item) {

    }

    /**
     * 对拖拽的元素进行排序
     * @param fromPosition
     * @param toPosition
     */
    public void itemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(getData(), i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(getData(), i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }
}
