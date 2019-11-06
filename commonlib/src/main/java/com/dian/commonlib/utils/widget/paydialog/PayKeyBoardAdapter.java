package com.dian.commonlib.utils.widget.paydialog;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dian.commonlib.R;

import java.util.ArrayList;

/**
 * Created by kennysun on 2019/8/29.
 */

public class PayKeyBoardAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> valueList;

    public PayKeyBoardAdapter(Context context, ArrayList<String> valueList) {
        this.context = context;
        this.valueList = valueList;
    }

    @Override
    public int getCount() {
        return this.valueList == null ? 0 : this.valueList.size();
    }

    @Override
    public Object getItem(int position) {
        return valueList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).inflate(R.layout.item_pay_keyboard, null);
            viewHolder = new ViewHolder();
            viewHolder.tvValue = convertView.findViewById(R.id.tvValue);
            viewHolder.btnDelete = convertView.findViewById(R.id.btnDelete);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        String currentVal = this.valueList.get(position);
        if(currentVal.equals("del")){
            viewHolder.btnDelete.setVisibility(View.VISIBLE);
            viewHolder.tvValue.setVisibility(View.GONE);
        } else {
            viewHolder.btnDelete.setVisibility(View.GONE);
            viewHolder.tvValue.setVisibility(View.VISIBLE);
            viewHolder.tvValue.setText(currentVal);
        }
        if(currentVal.equals("del") || currentVal.equals(" ")){
            convertView.setBackgroundColor(Color.parseColor("#E0E0E0"));
        } else {
            convertView.setBackgroundColor(Color.parseColor("#F5F5F5"));
        }

        return convertView;
    }

    public final class ViewHolder {
        public TextView tvValue;
        public View btnDelete;
    }

}