package com.huohuo.ui.main.msg;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;

import com.dian.commonlib.base.BaseLoadActivity;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.dian.commonlib.utils.widget.OnDrawableListener;
import com.dian.commonlib.utils.widget.SearchEditText;
import com.huohuo.R;
import com.huohuo.ui.adapter.SearchUserAdapter;
import com.huohuo.dao.table.Friend;
import com.huohuo.mvp.contract.msg.SearchUserContract;
import com.huohuo.mvp.presenter.msg.SearchUserPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 搜索用户  全网搜索
 * Created by kennysun on 2019/9/3.
 */

public class SearchUserActivity extends BaseLoadActivity implements SearchUserContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.etSearch)
    SearchEditText etSearch;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.multipleStatusView)
    MultipleStatusView multipleStatusView;

    private SearchUserPresenter searchUserPresenter;
    private SearchUserAdapter searchUserAdapter;
    List<Friend> friends = new ArrayList<>();

    @Override
    public void retry() {

    }

    @Override
    public MultipleStatusView getMultipleStatusView() {
        return multipleStatusView;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_search_user;
    }

    @Override
    public void initViewAndData() {
        super.initViewAndData();
        setToolbarConfig(toolbar, R.string.search);
        searchUserPresenter = new SearchUserPresenter();
        searchUserPresenter.attachView(this, this);
        etSearch.setHint(R.string.phone_num);
        etSearch.setInputType(InputType.TYPE_CLASS_NUMBER);
        recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        searchUserAdapter = new SearchUserAdapter(R.layout.item_search_user, new ArrayList<>());
        recyclerview.setAdapter(searchUserAdapter);
        etSearch.setOnSearchListener(() -> searchFriend());

        etSearch.setOnDrawableListener(new OnDrawableListener() {
            @Override
            public void onLeft(View v, Drawable left) {
                searchFriend();
            }

            @Override
            public void onRight(View v, Drawable right) {
                etSearch.setText("");
                etSearch.setHint(R.string.search_friend);
                multipleStatusView.showEmpty();
            }
        });
    }

    private void searchFriend() {
        String trim = etSearch.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            multipleStatusView.showEmpty();
        } else {
            searchUserPresenter.search(trim);
        }
    }


    @Override
    public void search(Friend list) {
        if (list != null) {
            friends.clear();
            friends.add(list);
            multipleStatusView.showContent();
            searchUserAdapter.setNewData(friends);
        } else {
            multipleStatusView.showEmpty();
        }
    }
}
