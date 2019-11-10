package com.huohuo.ui.main.msg;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseLoadActivity;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.dian.commonlib.utils.widget.OnTextChangeListener;
import com.dian.commonlib.utils.widget.SearchEditText;
import com.huohuo.R;
import com.huohuo.ui.adapter.GroupAdapter;
import com.huohuo.dao.table.Group;
import com.huohuo.mvp.contract.msg.GroupContract;
import com.huohuo.mvp.presenter.msg.GroupPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by kennysun on 2019/9/3.
 */

public class GroupActivity extends BaseLoadActivity implements GroupContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.etSearch)
    SearchEditText etSearch;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.multipleStatusView)
    MultipleStatusView multipleStatusView;
    private GroupAdapter groupAdapter;
    private List<Group> groups;

    private GroupPresenter groupPresenter;
    @Override
    public void retry() {

    }

    @Override
    public MultipleStatusView getMultipleStatusView() {
        return multipleStatusView;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_group;
    }

    @Override
    public void initViewAndData() {
        super.initViewAndData();
        etSearch.setHint(R.string.search_group);
        setToolbarConfig(toolbar, R.string.group_chat);

        groupPresenter = new GroupPresenter();
        groupPresenter.attachView(this,this);

        groupAdapter = new GroupAdapter(R.layout.item_group, new ArrayList<>());
        recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerview.setAdapter(groupAdapter);
        groupAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//todo 群聊
            }
        });

        etSearch.addTextChangedListener(new OnTextChangeListener() {
            @Override
            public void afterTextChanged(Editable s) {
                searchGroup();
            }
        });

        groupPresenter.getGroups();
    }

    private void searchGroup() {
        String searchStr = etSearch.getText().toString();
        if (TextUtils.isEmpty(searchStr)) {
            groupAdapter.setNewData(groups);
        } else {
            List<Group> searchList = new ArrayList<>();
            for (Group group : groups) {
                if (group.getGroupName().contains(searchStr)) {
                    searchList.add(group);
                }
            }
            groupAdapter.setNewData(searchList);
        }
    }

    @Override
    public void groups(List<Group> groups) {
        this.groups = groups;
        groupAdapter.setNewData(groups);
    }
}
