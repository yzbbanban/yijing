package com.huohuo.ui.main.msg;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;

import com.dian.commonlib.base.BaseLoadActivity;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.dian.commonlib.utils.widget.OnDrawableListener;
import com.dian.commonlib.utils.widget.OnTextChangeListener;
import com.dian.commonlib.utils.widget.SearchEditText;
import com.huohuo.R;
import com.huohuo.ui.adapter.SearchFriendAdapter;
import com.huohuo.dao.table.Friend;
import com.huohuo.mvp.contract.msg.SearchFriendContract;
import com.huohuo.mvp.presenter.msg.SearchFriendPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 搜索好友  本地搜索
 * Created by kennysun on 2019/9/3.
 */

public class SearchFriendActivity extends BaseLoadActivity implements SearchFriendContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.etSearch)
    SearchEditText etSearch;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.multipleStatusView)
    MultipleStatusView multipleStatusView;

    private SearchFriendAdapter searchFriendAdapter;
    private SearchFriendPresenter searchFriendPresenter;

    @Override
    public void retry() {

    }

    @Override
    public MultipleStatusView getMultipleStatusView() {
        return multipleStatusView;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_search_friend;
    }

    @Override
    public void initViewAndData() {
        super.initViewAndData();
        setToolbarConfig(toolbar,R.string.search);
        searchFriendPresenter = new SearchFriendPresenter();
        searchFriendPresenter.attachView(this, this);
        etSearch.setHint(R.string.search_friend);
        recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        searchFriendAdapter = new SearchFriendAdapter(R.layout.item_search_friend, new ArrayList<>());
        recyclerview.setAdapter(searchFriendAdapter);
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
            }
        });
        etSearch.addTextChangedListener(new OnTextChangeListener(){
            @Override
            public void afterTextChanged(Editable s) {
                searchFriend();
            }
        });
    }

    private void searchFriend() {
        String trim = etSearch.getText().toString().trim();
        searchFriendAdapter.setSearchKey(trim);
        if (TextUtils.isEmpty(trim)) {
            multipleStatusView.showEmpty();
        } else {
            searchFriendPresenter.search(trim);
        }
    }

    @Override
    public void search(List<Friend> list) {
        if (list != null && list.size() > 0) {
            multipleStatusView.showContent();
            searchFriendAdapter.setNewData(list);
        } else {
            multipleStatusView.showEmpty();
        }
    }
}
