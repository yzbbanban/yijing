package com.yjb.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dian.commonlib.glide.GlideEngine;
import com.yjb.BuildConfig;
import com.yjb.R;
import com.yjb.dao.table.Friend;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

/**
 * Created by kennysun on 2019/9/3.
 */

public class FriendHeadAdapter extends HeadAdapter<RecyclerView.ViewHolder, Friend> implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder> {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_friend, parent, false);
        return new RecyclerView.ViewHolder(view) {
        };
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final Friend friend = getItem(position);
        TextView tvName = holder.itemView.findViewById(R.id.tvName);
        ImageView ivAvatar = holder.itemView.findViewById(R.id.ivAvatar);
        GlideEngine.loadRound(ivAvatar, BuildConfig.API_IMG_HOST + friend.getHeadImage());
        tvName.setText(TextUtils.isEmpty(friend.getFriendRemark()) ? friend.getNickName() : friend.getFriendRemark());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null)
                    onItemClickListener.onItemClick(friend);
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
        String firstLetter = String.valueOf(getItem(position).getFirstChar().charAt(0));
        if ("#".equals(firstLetter)) {
            textView.setText("#");
        } else {
            textView.setText(getItem(position).getFirstChar());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Friend friend);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
