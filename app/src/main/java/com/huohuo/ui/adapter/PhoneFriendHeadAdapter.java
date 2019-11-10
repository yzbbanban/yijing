package com.huohuo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dian.commonlib.glide.GlideEngine;
import com.huohuo.BuildConfig;
import com.huohuo.R;
import com.huohuo.dao.table.Friend;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

/**
 * Created by kennysun on 2019/9/3.
 */

public class PhoneFriendHeadAdapter extends HeadAdapter<RecyclerView.ViewHolder,Friend> implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder> {
    private Context context;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_phone_friend, parent, false);
        return new RecyclerView.ViewHolder(view) {
        };
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        String item = getItem(position).getRealName();
        ImageView ivHead = holder.itemView.findViewById(R.id.ivAvatar);
        TextView tvName = holder.itemView.findViewById(R.id.tvName);
        TextView tvNickName = holder.itemView.findViewById(R.id.tvNickName);
        Button btnAdd = holder.itemView.findViewById(R.id.btRight);

        tvName.setText(item);
        tvNickName.setText(TextUtils.isEmpty(getItem(position).getNickName()) ? getItem(position).getMobile() : getItem(position).getNickName());
        if (!getItem(position).getRegister()) {
            btnAdd.setText(R.string.invite);
            Glide.with(context).load(R.drawable.contact_default_avatar).into(ivHead);
            btnAdd.setBackgroundResource(R.drawable.shape_radio_ok_bt_enable);
            btnAdd.setTextColor(context.getResources().getColor(R.color.colorWhiteTextImportant));
        } else {
            GlideEngine.loadRound(ivHead,BuildConfig.API_IMG_HOST+getItem(position).getHeadImage());
            btnAdd.setText(getItem(position).getBecomeFriend() ? R.string.added : R.string.add);
            btnAdd.setBackgroundResource(!getItem(position).getBecomeFriend() ? R.drawable.shape_radio_bt_enable :
                    R.color.colorPrimary);
            btnAdd.setTextColor(getItem(position).getBecomeFriend() ? context.getResources().getColor(R.color.colorBlackTextSub) : context.getResources().getColor(R.color.colorWhiteTextImportant));
        }
        btnAdd.setEnabled(!getItem(position).getBecomeFriend());
        btnAdd.setOnClickListener(v -> {
            if (addFriendsClickListener != null) {
                Long friendUid = getItem(position).getFriendUid();
                addFriendsClickListener.addFriendsClick(getItem(position).getRegister(), friendUid == null ? 0 : friendUid.intValue(), position);
            }

        });

    }

    @Override
    public long getHeaderId(int position) {
        return getItem(position).getFirstChar().charAt(0);
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_phonecode_header, parent, false);
        return new RecyclerView.ViewHolder(view) {
        };
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView textView = (TextView) holder.itemView;
        String firstLetter = String.valueOf(getItem(position).getFirstChar());
        if ("#".equals(firstLetter)) {
            textView.setText("#");
        } else {
            textView.setText(String.valueOf(getItem(position).getFirstChar()));
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Friend friend);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    //添加好友按钮
    public interface AddFriendsClickListener {
        void addFriendsClick(boolean isRegister, int friendUid, int position);
    }

    public AddFriendsClickListener addFriendsClickListener;

    public void setAddFriendsClickListener(AddFriendsClickListener addFriendsClickListener) {
        this.addFriendsClickListener = addFriendsClickListener;
    }
}

