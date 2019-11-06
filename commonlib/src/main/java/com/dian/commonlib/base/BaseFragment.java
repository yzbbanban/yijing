package com.dian.commonlib.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dian.commonlib.R;
import com.trello.rxlifecycle2.components.support.RxFragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

/**
 * Created by kennysun on 2019/8/7.
 */

abstract public class BaseFragment extends RxFragment {
    private BaseActivity mActivity;

   public abstract int getLayout();

   public abstract void initViewAndData();

    /**
     * 子类是否可接受eventbus事件
     */
    public boolean canRegisterEventbus() {
        return false;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewAndData();
    }


    @Override
    public void onStart() {
        super.onStart();
        if (canRegisterEventbus()) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (canRegisterEventbus()) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof BaseActivity) {
            this.mActivity = (BaseActivity) activity;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }


    /**
     * 配置toolbar
     */
    public Toolbar setToolbarConfig(Toolbar toolbar, int title, int naIcon) {
        toolbar.setNavigationIcon(naIcon);
        toolbar.setTitle(title);
        mActivity.setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationOnClick();
            }
        });
        return toolbar;
    }

    public Toolbar setToolbarConfig(Toolbar toolbar, int title) {
        return setToolbarConfig(toolbar, title, R.drawable.ic_action_back);
    }

    /**
     * 标题左边按钮点击事件
     */
    public void navigationOnClick() {
        mActivity.navigationOnClick();
    }


    public BaseActivity getBaseActivity() {
        return mActivity;
    }

    protected void openActivity(Class<?> clazz) {
        Intent intent = new Intent(getBaseActivity(), clazz);
        startActivity(intent);
    }


    /**
     * 处理接口返回的错误信息
     */
    public void handHttpCode(int code) {
        mActivity.handHttpCode(code);
    }

}
