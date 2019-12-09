package com.yjb.ui.adapter;

import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseMyViewHolder;
import com.yjb.BuildConfig;
import com.yjb.R;
import com.yjb.dao.table.Friend;

import java.util.List;

/**
 * Created by kennysun on 2019/9/4.
 */

public class SearchFriendAdapter extends BaseQuickAdapter<Friend,BaseMyViewHolder> {
    private String searchKey = "";

    public SearchFriendAdapter(int layoutResId, @Nullable List<Friend> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseMyViewHolder helper, Friend item) {
        helper.loadRoundImage(R.id.ivAvatar, "" + item.getHeadImage());
        TextView tvName = helper.getView(R.id.tvName);
        TextView tvSearch = helper.getView(R.id.tvSearch);
        String remarkName = item.getNickName();
        String nickName = item.getNickName();
        String account = item.getMobile();
        String name = "";
        String search = "";
        if (TextUtils.isEmpty(remarkName)) {
            name = nickName;
        } else {
            name = remarkName;
        }

        if (account.contains(searchKey)) {
            search = mContext.getResources().getString(R.string.phone_lable) + account;
        }

        if (nickName.contains(searchKey)) {
            search = mContext.getResources().getString(R.string.nickname_lable) + nickName;
        }

        ForegroundColorSpan searchKeySpan = new ForegroundColorSpan(mContext.getResources().getColor(R.color.colorAccent));
        if (name.contains(searchKey)) {//显示的名字中包含搜索关键字，则不显示二级标题
            tvSearch.setVisibility(View.GONE);
            //设置一级标题的展示
            SpannableStringBuilder spannableRemarkName;
            //昵称不是空，且昵称含有关键字
            int searchKeyStart = name.indexOf(searchKey);//关键字的起始位置
            int searchKeyEnd = searchKey.length() + searchKeyStart;//关键字的结束位置
            if (searchKeyStart != -1) {
                spannableRemarkName = new SpannableStringBuilder(name);
                spannableRemarkName.setSpan(searchKeySpan, searchKeyStart, searchKeyEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                tvName.setText(spannableRemarkName);
            }
        } else {
            tvName.setText(name);
            //设置二级标题的显示
            SpannableStringBuilder spannableRemarkSub;
            if (!TextUtils.isEmpty(search)) {
                //昵称不是空，且昵称含有关键字
                if (search.contains(searchKey)) {
                    tvSearch.setVisibility(View.VISIBLE);
                    int searchKeyStart = search.indexOf(searchKey);//关键字的起始位置
                    int searchKeyEnd = searchKey.length() + searchKeyStart;//关键字的结束位置
                    if (searchKeyStart != -1) {
                        spannableRemarkSub = new SpannableStringBuilder(search);
                        spannableRemarkSub.setSpan(searchKeySpan, searchKeyStart, searchKeyEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        tvSearch.setText(spannableRemarkSub);
                    }
                } else {
                    tvSearch.setVisibility(View.GONE);
                }
            } else {
                tvSearch.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 设置搜索的关键字
     *
     * @param trim
     */
    public void setSearchKey(String trim) {
        searchKey = trim;
    }
}
