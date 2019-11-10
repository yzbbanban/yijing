package com.huohuo.ui.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseMyViewHolder;
import com.huohuo.BuildConfig;
import com.huohuo.R;
import com.huohuo.dao.table.ShopRecordDetail;

import java.util.List;

/**
 * Created by kennysun on 2019/9/5.
 */

public class ShopRecordAdapter extends BaseQuickAdapter<ShopRecordDetail, BaseMyViewHolder> {
    public ShopRecordAdapter(int layoutResId, @Nullable List<ShopRecordDetail> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseMyViewHolder helper, ShopRecordDetail item) {
        helper.setText(R.id.tvShopRecordPay, "分值: " + item.getPrice());
        helper.setText(R.id.tvShopRecordStatus, item.getStatus() == 1 ? "未领取" : "已领取");
        helper.setText(R.id.tvShopRecordTime, "兑换时间:" + item.getTime());
        helper.setText(R.id.tvShopRecordTitle, item.getName());

        String groupHead = item.getUrl();//自定义头像
        if (TextUtils.isEmpty(groupHead)) {
            helper.loadImage(R.id.ivShopRecordImage, R.drawable.group_icon);
        } else {
            helper.loadRoundImage(R.id.ivShopRecordImage, BuildConfig.API_IMG_HOST + groupHead);
        }


    }
}
