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
            helper.loadRoundImage(R.id.ivImage, "" + groupHead);
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

        //一颗星：30分-60分
        //二颗星：60分-200分
        //三颗星：200分-400分
        //四颗星：400分-600分
        //五颗星：600分以上
        int score = item.getScore();
        float rate = 0;
        if (score >= 30 && score < 60) {
            rate = 1.0f;
        } else if (score >= 60 && score < 200) {
            rate = 2.0f;
        } else if (score >= 200 && score < 400) {
            rate = 3.0f;
        } else if (score >= 400 && score < 600) {
            rate = 4.0f;
        } else if (score >= 600) {
            rate = 5.0f;
        }
        helper.setRating(R.id.rbScore, rate);

    }
}
