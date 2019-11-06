package com.huohuo.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseMyViewHolder;
import com.huohuo.R;
import com.huohuo.mvp.model.bean.ModuleBean;

import java.util.List;

/**
 * Created by kennysun on 2019/9/2.
 */

public class ModuleEditAdapter extends BaseQuickAdapter<ModuleBean, BaseMyViewHolder> {
    public ModuleEditAdapter(int layoutResId, @Nullable List<ModuleBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseMyViewHolder helper, ModuleBean item) {
        RecyclerView rvAdded = helper.getView(R.id.rvAdded);
        rvAdded.setLayoutManager(new GridLayoutManager(mContext, 4, LinearLayoutManager.VERTICAL, false));
        ModuleEditItemAdapter moduleEditItemAdapter = new ModuleEditItemAdapter(R.layout.item_module_edit_item, item.getModuleItems());
        //已添加才能拖拽排序
        moduleEditItemAdapter.setOnItemLongClickListener((adapter, view, position) -> false);
        ItemDragCallback itemDragCallback = new ItemDragCallback(moduleEditItemAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragCallback);
        itemTouchHelper.attachToRecyclerView(rvAdded);

        rvAdded.setAdapter(moduleEditItemAdapter);
    }

}
