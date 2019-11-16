package com.huohuo.ui.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseMyViewHolder;
import com.huohuo.BuildConfig;
import com.huohuo.R;
import com.huohuo.dao.table.Group;
import com.huohuo.dao.table.YiFcDetail;

import java.util.List;

import butterknife.BindView;

/**
 * Created by kennysun on 2019/9/5.
 */

public class YiFcDetailAdapter extends BaseQuickAdapter<YiFcDetail, BaseMyViewHolder> {

    public YiFcDetailAdapter(int layoutResId, @Nullable List<YiFcDetail> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseMyViewHolder helper, YiFcDetail item) {
        helper.setText(R.id.tvName, item.getTitle());
        String groupHead = item.getUrl();
        if (TextUtils.isEmpty(groupHead)) {
            helper.loadImage(R.id.ivAvatar, R.drawable.group_icon);
        } else {
            helper.loadRoundImage(R.id.ivAvatar, BuildConfig.API_IMG_HOST + groupHead);
        }

    }
}
