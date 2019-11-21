package com.yjb.ui.main.msg;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dian.commonlib.base.BaseLoadActivity;
import com.dian.commonlib.utils.AppUtil;
import com.dian.commonlib.utils.ToastUtil;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.yjb.R;
import com.yjb.mvp.contract.home.NewsViewIncContract;
import com.yjb.mvp.model.bean.NewsList;
import com.yjb.mvp.presenter.home.NVIcPresenter;

import butterknife.BindView;

public class NewsDetailActivity extends BaseLoadActivity implements NewsViewIncContract.View {

    private static final String TAG = "NewsDetailActivity";

    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvNewsTitle)
    TextView tvNewsTitle;
    @BindView(R.id.tvNewsType)
    TextView tvNewsType;
    @BindView(R.id.tvNewsTime)
    TextView tvNewsTime;
    @BindView(R.id.tvNewsRead)
    TextView tvNewsRead;
    @BindView(R.id.tvNewsContent)
    TextView tvNewsContent;

    private NVIcPresenter nvIcPresenter;

    private static final String NEWS = "news";
    private NewsList.ListBean newsData;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        newsData = (NewsList.ListBean) intent.getExtras().getSerializable(NEWS);
        Log.i(TAG, "onNewIntent: " + newsData);
    }

    @Override
    public void initViewAndData() {
        newsData = (NewsList.ListBean) getIntent().getExtras().getSerializable(NEWS);
        super.initViewAndData();
        ivLeft.setVisibility(View.VISIBLE);
        ivLeft.setOnClickListener(view -> {
            finish();
        });
        nvIcPresenter = new NVIcPresenter();
        nvIcPresenter.attachView(this, this);
        nvIcPresenter.getList(AppUtil.getToken(), "" + newsData.getId());
        tvNewsTitle.setText("" + newsData.getTitle());
        tvNewsType.setText("");
        tvNewsTime.setText("" + newsData.getCreatetime_text());
        tvNewsRead.setText(newsData.getView() + "已读");
        tvNewsContent.setText(newsData.getContent());

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_news_detail;
    }


    @Override
    public void retry() {

    }

    @Override
    public MultipleStatusView getMultipleStatusView() {
        return null;
    }

    @Override
    public void getNVIcSuccess(Object o) {
        ToastUtil.show(this, "已读信息");
    }
}
