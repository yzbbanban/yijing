package com.yjb.ui.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseMyViewHolder;
import com.yjb.BuildConfig;
import com.yjb.R;
import com.yjb.mvp.model.bean.RankList;

import java.util.List;

/**
 * Created by kennysun on 2019/9/5.
 */

public class ScoreYjAdapter extends BaseQuickAdapter<RankList.ListBean, BaseMyViewHolder> {
    public ScoreYjAdapter(int layoutResId, @Nullable List<RankList.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseMyViewHolder helper, RankList.ListBean item) {
        helper.setText(R.id.tvName, "" + item.getNickname());
        helper.setText(R.id.tvDetailRemark, "积分：" + item.getScore());
        helper.setText(R.id.tvDetailTime, "活动时间：" + item.getWorktime());
        String groupHead = item.getPhotoimage();//自定义头像
        if (TextUtils.isEmpty(groupHead)) {
            helper.loadImage(R.id.ivImage, R.drawable.group_icon);
        } else {
            helper.loadImage(R.id.ivImage, BuildConfig.API_IMG_HOST + groupHead);
        }

        int index = item.getIndex() == 0 ? 4 : item.getIndex();
        helper.setText(R.id.tvIndex, "" + index);
        switch (index) {
            case 1:
                helper.setBackgroundRes(R.id.tvIndex, R.drawable.order1);
                break;
            case 2:
                helper.setBackgroundRes(R.id.tvIndex, R.drawable.order2);
                break;
            case 3:
                helper.setBackgroundRes(R.id.tvIndex, R.drawable.order3);
                break;
            default:
                helper.setBackgroundRes(R.id.tvIndex, R.drawable.orderother);
                break;
        }

    }
}
