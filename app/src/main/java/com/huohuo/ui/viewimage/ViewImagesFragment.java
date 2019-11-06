/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.huohuo.ui.viewimage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.dian.commonlib.base.BaseLoadActivity;
import com.dian.commonlib.base.BaseLoadFragment;
import com.dian.commonlib.glide.GlideEngine;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.dian.commonlib.utils.widget.MyBottomSheetDialog;
import com.google.zxing.Result;
import com.huohuo.R;
import com.huohuo.mvp.presenter.sys.ScanPresenter;
import com.huohuo.ui.scan.QRHandleUtil;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ViewImagesFragment extends BaseLoadFragment {
    private static final String IMAGE_DATA_EXTRA = "image";
    private String image;
    private PhotoView mImageView;
    private MultipleStatusView multipleStatusView;
    private PhotoViewAttacher.OnViewTapListener onViewTapListener;

    public static ViewImagesFragment newInstance(String imageUrl, PhotoViewAttacher.OnViewTapListener onViewTapListener) {
        final ViewImagesFragment f = new ViewImagesFragment();

        final Bundle args = new Bundle();
        args.putCharSequence(IMAGE_DATA_EXTRA, imageUrl);
        f.setArguments(args);

        f.onViewTapListener = onViewTapListener;

        return f;
    }

    public ViewImagesFragment() {
    }


    @Override
    public int getLayout() {
        return R.layout.fragment_view_image;
    }

    @Override
    public void initViewAndData() {
        super.initViewAndData();
        image = getArguments() != null ? getArguments().getString(IMAGE_DATA_EXTRA) : "";

        if (ViewImagesActivity.class.isInstance(getActivity())) {
            GlideEngine.loadRound(mImageView,image);
        }
        mImageView.setOnViewTapListener(onViewTapListener);
        mImageView.setOnLongClickListener(v -> {
           new ViewImgScanDialog(getBaseActivity()).setImage(image).show();
            return false;
        });
    }
    public void cancelWork() {
        mImageView.setImageDrawable(null);
        mImageView = null;
    }

    @Override
    protected void retry() {

    }

    @Override
    protected MultipleStatusView getMultipleStatusView() {
        return multipleStatusView;
    }
}
