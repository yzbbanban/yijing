package com.yjb.ui.adapter;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by kennysun on 2019/9/3.
 */

public abstract class HeadAdapter <VH extends RecyclerView.ViewHolder,T> extends RecyclerView.Adapter<VH> {

    private ArrayList<T> items = new ArrayList<T>();

    public HeadAdapter() {
        setHasStableIds(true);
    }

    public void add(T object) {
        items.add(object);
        notifyDataSetChanged();
    }

    public void add(int index, T object) {
        items.add(index, object);
        notifyDataSetChanged();
    }

    public void addAll(Collection<? extends T> collection) {
        if (collection != null) {
            items.addAll(collection);
            notifyDataSetChanged();
        }
    }

    public void setDatas(Collection<? extends T> collection) {
        this.items.clear();
        if (collection != null) {
            this.items.addAll(collection);
        }
        notifyDataSetChanged();
    }

    public void addAll(T... items) {
        addAll(Arrays.asList(items));
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public void remove(T object) {
        items.remove(object);
        notifyDataSetChanged();
    }

    public T getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
//
}
