package com.yjb.ui.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseMyViewHolder;
import com.yjb.BuildConfig;
import com.yjb.R;
import com.yjb.mvp.model.bean.ExchangeList;

import java.util.List;

/**
 * Created by kennysun on 2019/9/5.
 */

public class ShopRecordAdapter extends BaseQuickAdapter<ExchangeList.ListBean, BaseMyViewHolder> {
    public ShopRecordAdapter(int layoutResId, @Nullable List<ExchangeList.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseMyViewHolder helper, ExchangeList.ListBean item) {
        helper.setText(R.id.tvShopRecordPay, "分值: " + item.getRemark());
        helper.setText(R.id.tvShopRecordStatus, "" + item.getStatus_text());
        helper.setText(R.id.tvShopRecordTime, "兑换时间:" + item.getCreatetime_text());
        helper.setText(R.id.tvShopRecordTitle, "" + item.getMall_id());

        String groupHead = item.getMallid_text().getProductimage();//自定义头像
        if (TextUtils.isEmpty(groupHead)) {
            helper.loadImage(R.id.ivShopRecordImage, R.drawable.group_icon);
        } else {
            helper.loadRoundImage(R.id.ivShopRecordImage, BuildConfig.API_IMG_HOST + groupHead);
        }


    }
}
