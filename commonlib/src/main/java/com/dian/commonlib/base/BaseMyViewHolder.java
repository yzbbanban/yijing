package com.dian.commonlib.base;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.dian.commonlib.glide.GlideEngine;

/**
 * Created by kennysun on 2019/8/7.
 */

public class BaseMyViewHolder extends BaseViewHolder {
    public BaseMyViewHolder(View view) {
        super(view);
    }

    /**
     * 固定高度
     *
     * @param resId
     * @param imgUrl
     * @return
     */
    public BaseMyViewHolder loadImage(int resId, String imgUrl) {
        ImageView imageView = getView(resId);
        GlideEngine.load(imageView, imgUrl);
        return this;
    }

    public BaseMyViewHolder loadImage(int resId, int imgResId) {
        ImageView imageView = getView(resId);
//        GlideEngine.load(imageView, imgResId);
        return this;
    }

    /**
     * 加载头像
     *
     * @param resId
     * @param imgUrl
     * @return
     */
    public BaseMyViewHolder loadRoundImage(int resId, String imgUrl) {
        ImageView imageView = getView(resId);
        GlideEngine.loadRound(imageView, imgUrl);
        return this;
    }

    /**
     * 固定高度
     *
     * @param resId
     * @param imgUrl
     * @return
     */
    public BaseMyViewHolder loadRadioImage(int resId, String imgUrl,int radio) {
        ImageView imageView = getView(resId);
        GlideEngine.loadRadio(imageView, imgUrl,radio);
        return this;
    }

    public BaseMyViewHolder loadRadioImage(int resId, int imgResId,int radio) {
        ImageView imageView = getView(resId);
        GlideEngine.loadRadio(imageView, imgResId,radio);
        return this;
    }

}
