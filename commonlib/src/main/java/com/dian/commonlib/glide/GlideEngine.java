package com.dian.commonlib.glide;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.zhihu.matisse.engine.ImageEngine;

/**
 * Created by kennysun on 2019/8/7.
 */

public class GlideEngine implements ImageEngine {

    public static RequestOptions defaultRequestOptions = new RequestOptions().placeholder(new ColorDrawable(Color.GRAY))
            .error(new ColorDrawable(Color.RED))
            .fallback(new ColorDrawable(Color.RED));

    public static void loadRound(ImageView imageView, String url) {
        Glide.with(imageView)
                .load(url)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(imageView);
    }

    public static void loadRound(ImageView imageView, int resId) {
        Glide.with(imageView)
                .load(resId)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(imageView);
    }
    public static void loadRadio(ImageView imageView, String url,int radio) {
        Glide.with(imageView)
                .load(url)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(radio)))
                .into(imageView);
    }
    public static void loadRadio(ImageView imageView, int url,int radio) {
        Glide.with(imageView)
                .load(url)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(radio)))
                .into(imageView);
    }
    public static void loadNoPlaceholder(ImageView imageView, String url) {
        Glide.with(imageView)
                .load(url)
                .into(imageView);
    }
    public static void load(ImageView imageView, String url) {
        Glide.with(imageView)
                .load(url)
                .apply(defaultRequestOptions)
                .into(imageView);
    }

    public static void load(ImageView imageView, int resId) {
        Glide.with(imageView)
                .load(resId)
                .apply(defaultRequestOptions)
                .into(imageView);
    }

    public static void stopLoad(ImageView imageView) {
        Glide.with(imageView).onStop();
    }

    public static void startLoad(ImageView imageView) {
        Glide.with(imageView).onStart();
    }

    @Override
    public void loadThumbnail(int resize, Drawable placeholder, ImageView imageView, Uri uri) {
        Glide.with(imageView)
                .asBitmap()  // some .jpeg files are actually gif
                .load(uri)
                .apply(new RequestOptions().placeholder(placeholder)
                        .override(resize, resize)
                        .centerCrop()
                )
                .into(imageView);
    }

    @Override
    public void loadGifThumbnail(int resize, Drawable placeholder, ImageView imageView, Uri uri) {
        Glide.with(imageView)
                .asGif()  // some .jpeg files are actually gif
                .load(uri)
                .apply(new RequestOptions().placeholder(placeholder)
                        .override(resize, resize)
                        .centerCrop()
                )
                .into(imageView);
    }

    @Override
    public void loadImage(int resizeX, int resizeY, ImageView imageView, Uri uri) {
        Glide.with(imageView)
                .load(uri)
                .apply(new RequestOptions().override(resizeX, resizeY)
                        .priority(Priority.HIGH)
                        .fitCenter()
                )
                .into(imageView);
    }

    @Override
    public void loadGifImage(int resizeX, int resizeY, ImageView imageView, Uri uri) {
        Glide.with(imageView)
                .asGif()
                .load(uri)
                .apply(new RequestOptions().override(resizeX, resizeY)
                        .priority(Priority.HIGH)
                )
                .into(imageView);
    }

    @Override
    public boolean supportAnimatedGif() {
        return true;
    }
}
