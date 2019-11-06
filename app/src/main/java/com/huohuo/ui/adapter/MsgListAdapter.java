package com.huohuo.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseMyViewHolder;
import com.dian.commonlib.utils.ToastUtil;
import com.huohuo.R;
import com.huohuo.dao.table.ChatList;
import com.huohuo.ui.main.MainActivity;

import java.util.List;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

/**
 * Created by kennysun on 2019/9/2.
 */

public class MsgListAdapter extends BaseQuickAdapter<ChatList, BaseMyViewHolder> {
    public MsgListAdapter(int layoutResId, @Nullable List<ChatList> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseMyViewHolder helper, ChatList item) {
        int i = getData().indexOf(item);
        LinearLayout llAvatar = helper.getView(R.id.llAvatar);
        Badge badge = new QBadgeView(mContext)
                .setBadgeBackgroundColor(mContext.getResources().getColor(R.color.colorAccent))
                .setGravityOffset(6f, 0f, true)
                .bindTarget(llAvatar)
                .setBadgeTextSize(9, true)
                .setOnDragStateChangedListener((dragState, badge1, targetView) -> {
                    if (Badge.OnDragStateChangedListener.STATE_SUCCEED == dragState) {
                        ToastUtil.show(mContext, "移除了,parentPosition=" + i);
                    }
                });

            badge.setBadgeNumber(1);
            badge.setBadgeTextColor(mContext.getResources().getColor(R.color.colorAccent));
            helper.setText(R.id.tvMsgTarget, R.string.sys_msg);
            helper.setVisible(R.id.tvMsgContentLable, View.GONE);
            helper.loadImage(R.id.ivIcon,R.drawable.ic_next);
            helper.loadImage(R.id.ivAvatar,R.drawable.sys_notice);

    }
}
