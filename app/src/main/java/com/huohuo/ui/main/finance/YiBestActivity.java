package com.huohuo.ui.main.finance;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseLoadActivity;
import com.dian.commonlib.utils.ToastUtil;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.huohuo.R;
import com.huohuo.mvp.model.bean.ModuleBean;
import com.huohuo.mvp.model.bean.ModuleItemBean;
import com.huohuo.ui.adapter.YxYjModuleAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class YiBestActivity extends BaseLoadActivity {


    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private YxYjModuleAdapter yiFcModuleAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_yi_best;
    }

    @Override
    public void retry() {

    }

    @Override
    public void initViewAndData() {
        super.initViewAndData();
        tvTitle.setText("优秀义警");
        ivLeft.setVisibility(View.VISIBLE);
        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initModule();
    }

    @Override
    public MultipleStatusView getMultipleStatusView() {
        return null;
    }


    private void initModule() {
        recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        List<ModuleBean> list = new ArrayList<>();
        ModuleBean m1 = new ModuleBean();
        m1.setTitle("邻里守望");
        m1.setType(1);
        List<ModuleItemBean> mList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ModuleItemBean itemBean = new ModuleItemBean();
            itemBean.setIndex(i + 1);
            itemBean.setName("JA" + i);
            itemBean.setPhotoUrl("");
            mList.add(itemBean);
        }
        m1.setModuleItems(mList);
        list.add(m1);
        ModuleBean m2 = new ModuleBean();
        m2.setTitle("法制宣传");
        m2.setType(2);
        List<ModuleItemBean> mList2 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ModuleItemBean itemBean = new ModuleItemBean();
            itemBean.setIndex(i + 1);
            itemBean.setName("IAD" + i);
            itemBean.setPhotoUrl("");
            mList2.add(itemBean);
        }
        m2.setModuleItems(mList2);
        list.add(m2);

        ModuleBean m3 = new ModuleBean();
        m3.setTitle("扶贫关爱");
        m3.setType(2);
        List<ModuleItemBean> mList3 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ModuleItemBean itemBean = new ModuleItemBean();
            itemBean.setIndex(i + 1);
            itemBean.setName("IAD" + i);
            itemBean.setPhotoUrl("");
            mList3.add(itemBean);
        }
        m3.setModuleItems(mList3);
        list.add(m3);
        yiFcModuleAdapter = new YxYjModuleAdapter(R.layout.item_yifc_module, list);
        recyclerview.setAdapter(yiFcModuleAdapter);
        yiFcModuleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtil.show(YiBestActivity.this, "+++" + list.get(position));
                Intent intent = new Intent(YiBestActivity.this, DuiWuDetailActivity.class);
                intent.putExtra("TITLE", list.get(position).getTitle());
                startActivity(intent);
            }
        });

    }

}
