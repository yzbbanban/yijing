package com.dian.commonlib.utils.widget;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.R;
import com.dian.commonlib.base.BaseMyViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dian on 2019/3/4.
 */

public class BaseCheckAdapter<V extends BaseCheckModel> extends BaseQuickAdapter<V, BaseMyViewHolder> {
    private boolean singleCheck;//是否是单选
    private List<V> checkeds;
    private OnCheckAllListener onCheckAllListener;

    private RadioButton lastRadioButton;

    public void setOnCheckAllListener(OnCheckAllListener onCheckAllListener) {
        this.onCheckAllListener = onCheckAllListener;
    }

    public void setSingleCheck(boolean singleCheck) {
        this.singleCheck = singleCheck;
    }

    public BaseCheckAdapter(@Nullable List<V> data, boolean singleCheck) {
        this(R.layout.item_check, data);
        this.singleCheck = singleCheck;
        checkeds = new ArrayList<>();
    }

    private BaseCheckAdapter(int layoutResId, @Nullable List<V> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseMyViewHolder helper, V item) {
        CheckBox checkbox = helper.getView(R.id.checkbox);
        RadioButton radioButton = helper.getView(R.id.radioButton);
        TextView tvTitle = helper.getView(R.id.tvTitle);
        tvTitle.setText(item.getTitle());
        //初始化选中状态
        boolean check = item.check;
        if (singleCheck) {
            //单选
            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (lastRadioButton != null) {
                        lastRadioButton.setChecked(false);
                    }
                    lastRadioButton = radioButton;
                    item.setCheck(isChecked);
                    if (checkeds != null && checkeds.size() > 0) {
                        checkeds.clear();
                    }
                    checkeds.add(item);
                }
            });
            tvTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    radioButton.setChecked(true);
                }
            });
            radioButton.setVisibility(View.VISIBLE);
            checkbox.setVisibility(View.GONE);
            radioButton.setChecked(check);
        } else {
            //多选
            checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    item.setCheck(isChecked);
                    if (isChecked) {
                        if (!checkeds.contains(item)){
                            checkeds.add(item);
                        }
                    } else {
                        checkeds.remove(item);
                    }
                    if (onCheckAllListener != null) {
                        onCheckAllListener.checkAll(isCheckAll());
                    }
                }
            });
            tvTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkbox.isChecked()) {
                        checkbox.setChecked(false);
                    } else {
                        checkbox.setChecked(true);
                    }
                }
            });
            checkbox.setVisibility(View.VISIBLE);
            radioButton.setVisibility(View.GONE);
            checkbox.setChecked(check);
        }
    }

    public List<V> getResult() {
        return checkeds;
    }

    public void checkAll() {
        checkeds.clear();
        List<V> data = getData();
        for (V v : data) {
            v.setCheck(true);
            checkeds.add(v);
        }
        if (onCheckAllListener != null) {
            onCheckAllListener.checkAll(true);
        }
        notifyDataSetChanged();
    }

    public void cancleCheckAll() {
        for (V v : getData()) {
            v.setCheck(false);
        }
        checkeds.clear();
        if (onCheckAllListener != null) {
            onCheckAllListener.checkAll(false);
        }
        notifyDataSetChanged();
    }

    public boolean isCheckAll() {
        if (checkeds != null && checkeds.size() > 0) {
            if (getData().size() == checkeds.size()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public interface OnCheckAllListener {
        void checkAll(boolean checkAll);
    }
}
