package com.yjb.ui.main.msg;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.dian.commonlib.base.BaseLoadActivity;
import com.dian.commonlib.utils.RxPermissionsCallbackUtil;
import com.dian.commonlib.utils.RxPermissionsUtil;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.dian.commonlib.utils.widget.SearchEditText;
import com.yjb.R;
import com.yjb.app.HuoHuoConstants;
import com.yjb.ui.adapter.NewFriendAdapter;
import com.yjb.dao.table.FriendApply;
import com.yjb.mvp.contract.msg.NewFriendContract;
import com.yjb.mvp.presenter.msg.NewFriendPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by kennysun on 2019/9/3.
 */

public class NewFriendActivity extends BaseLoadActivity implements NewFriendContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.etSearch)
    SearchEditText etSearch;
    @BindView(R.id.clPhoneContract)
    ConstraintLayout clPhoneContract;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.multipleStatusView)
    MultipleStatusView multipleStatusView;
    private NewFriendAdapter newFriendAdapter;
    private NewFriendPresenter newFriendPresenter;

    private List<FriendApply> friendApplies;

    private int currentPosi;

    @Override
    public void retry() {

    }

    @Override
    public MultipleStatusView getMultipleStatusView() {
        return multipleStatusView;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_new_friend;
    }

    @Override
    public void initViewAndData() {
        super.initViewAndData();
        setToolbarConfig(toolbar, R.string.new_friend);
        friendApplies = new ArrayList<>();
        newFriendPresenter = new NewFriendPresenter();
        newFriendPresenter.attachView(this, this);

        etSearch.setFocusableInTouchMode(false);
        etSearch.setFocusable(false);
        etSearch.setHint(R.string.phone_num);

        recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        List<FriendApply> list = new ArrayList<>();
        newFriendAdapter = new NewFriendAdapter(R.layout.item_new_friend, list);
        recyclerview.setAdapter(newFriendAdapter);
        newFriendAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            currentPosi = position;
            FriendApply currentFriendApply = friendApplies.get(position);
            switch (view.getId()) {
                case R.id.btAgree:
                    newFriendPresenter.replyApply(currentFriendApply.getId().toString(), HuoHuoConstants.REPLY_FRIEND_APPLY_AGREE);
                    break;
                case R.id.btRefuse:
                    newFriendPresenter.replyApply(currentFriendApply.getId().toString(), HuoHuoConstants.REPLY_FRIEND_APPLY_REFUSE);
                    break;
            }
        });
        newFriendPresenter.listFriendApply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_new_friend_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addFriend:
                startActivity(new Intent(this, AddFriendActivity.class));
                break;
        }
        return true;
    }

    @OnClick({R.id.etSearch, R.id.clPhoneContract})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.etSearch:
                startActivity(new Intent(this, SearchUserActivity.class));
                break;
            case R.id.clPhoneContract:
                new RxPermissionsUtil(this).getPhoneContract(new RxPermissionsCallbackUtil(this,"") {
                    @Override
                    public void allAllow() {
                        startActivity(new Intent(NewFriendActivity.this, PhoneFriendActivity.class));
                    }
                });
                break;
        }
    }

    @Override
    public void friendApply(List<FriendApply> friends) {
        friendApplies = friends;
        newFriendAdapter.setNewData(friendApplies);
    }

    @Override
    public void agreeOrRefuseSuccess() {
        //删除列表当前操作的数据
        newFriendAdapter.remove(currentPosi);
    }
}
