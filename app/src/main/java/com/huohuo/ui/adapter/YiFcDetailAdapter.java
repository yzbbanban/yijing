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
    private int type;

    public YiFcDetailAdapter(int layoutResId, @Nullable List<YiFcDetail> data, int type) {
        super(layoutResId, data);
        this.type = type;
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
        if (type != 1) {

            int index = item.getIndex() == null ? 4 : item.getIndex();
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
            helper.setText(R.id.tvIndex, item.getIndex());
            helper.setText(R.id.tvDetail, item.getDetail());
            String image = item.getImage();
            if (TextUtils.isEmpty(image)) {
                helper.loadImage(R.id.ivImage, R.drawable.group_icon);
            } else {
                helper.loadRoundImage(R.id.ivImage, BuildConfig.API_IMG_HOST + groupHead);
            }
        }

    }
}
