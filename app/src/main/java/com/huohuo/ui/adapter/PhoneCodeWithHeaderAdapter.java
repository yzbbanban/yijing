package com.huohuo.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dian.commonlib.lang.MultiLanguageUtil;
import com.huohuo.R;
import com.huohuo.app.HuoHuoApp;
import com.huohuo.mvp.model.bean.CountryCodeBean;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

/**
 * Created by kennysun on 2019/8/29.
 */

public class PhoneCodeWithHeaderAdapter extends HeadAdapter<RecyclerView.ViewHolder,CountryCodeBean> implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder> {
    private int selectedLanguage;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_phone_code, parent, false);
        selectedLanguage = MultiLanguageUtil.getInstance().getLanguageType();
        return new RecyclerView.ViewHolder(view) {
        };
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        CountryCodeBean country = getItem(position);
        TextView tvCountry = (TextView) holder.itemView.findViewById(R.id.tvCountry);
        TextView tvCountyCode = (TextView) holder.itemView.findViewById(R.id.tvCountyCode);
        switch (selectedLanguage) {
            case MultiLanguageUtil.LANGUAGE_EN:
            case MultiLanguageUtil.LANGUAGE_KO:
                tvCountry.setText(country.getEnCountryName());
                break;
            default:
                tvCountry.setText(country.getCnCountryName());
                break;
        }
        tvCountyCode.setText(country.getCountryCode() + "");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null)
                    onItemClickListener.onItemClick(getItem(position));
            }
        });
    }

    @Override
    public long getHeaderId(int position) {
        return getItem(position).getCnFirstLetter().charAt(0);
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
        String firstLetter = getItem(position).getCnFirstLetter();
        if ("☆".equals(firstLetter)) {
            textView.setText(HuoHuoApp.mApp.getResources().getString(R.string.chang_yong));
        } else {
            textView.setText(String.valueOf(getItem(position).getCnFirstLetter()));
        }
    }

    public interface OnItemClickListener {
        void onItemClick(CountryCodeBean country);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
