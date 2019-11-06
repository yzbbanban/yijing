package com.huohuo.ui.main.home;

import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.dian.commonlib.base.BaseLoadActivity;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.huohuo.R;
import com.huohuo.mvp.model.bean.ModuleBean;
import com.huohuo.ui.adapter.ModuleEditAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by kennysun on 2019/9/2.
 */

public class ModuleEditActivity extends BaseLoadActivity {

    @BindView(R.id.btRight)
    AppCompatButton btRight;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    ModuleEditAdapter moduleEditAdapter;

    @Override
    public void retry() {

    }

    @Override
    public MultipleStatusView getMultipleStatusView() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_module_edit;
    }

    @Override
    public void initViewAndData() {
        super.initViewAndData();
        setToolbarConfig(toolbar, R.string.edit_my_module);
        btRight.setVisibility(View.VISIBLE);
        btRight.setText(R.string.finish);
        btRight.setEnabled(false);

        //todo 已添加监听，如果有变化则改变btRight的状态

        btRight.setOnClickListener(v -> {
            //todo 添加
        });

        recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        List<ModuleBean> list = new ArrayList<>();
        list.add(new ModuleBean());
        list.add(new ModuleBean());
        list.add(new ModuleBean());
        list.add(new ModuleBean());
        moduleEditAdapter = new ModuleEditAdapter(R.layout.item_module_edit, list);
        recyclerview.setAdapter(moduleEditAdapter);
    }
}
