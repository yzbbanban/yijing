package com.huohuo.ui.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseMyViewHolder;
import com.huohuo.BuildConfig;
import com.huohuo.R;
import com.huohuo.dao.table.ShopDetail;

import java.util.List;

/**
 * Created by kennysun on 2019/9/5.
 */

public class ShopAdapter extends BaseQuickAdapter<ShopDetail, BaseMyViewHolder> {


    public ShopAdapter(int layoutResId, @Nullable List<ShopDetail> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseMyViewHolder helper, ShopDetail item) {
        helper.setText(R.id.tvPay, "积分: " + item.getPrice());
        helper.setText(R.id.tvShopTitle, "" + item.getName());
        String groupHead = item.getUrl();//自定义头像
        if (TextUtils.isEmpty(groupHead)) {
            helper.loadImage(R.id.ivImage, R.drawable.group_icon);
        } else {
            helper.loadRoundImage(R.id.ivImage, BuildConfig.API_IMG_HOST + groupHead);
        }
        helper.addOnClickListener(R.id.acbBtnPay);

    }
}
