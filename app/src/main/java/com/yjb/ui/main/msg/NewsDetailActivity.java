package com.yjb.ui.main.msg;

import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.dian.commonlib.base.BaseLoadActivity;
import com.dian.commonlib.utils.AppUtil;
import com.dian.commonlib.utils.DateFormatUtil;
import com.dian.commonlib.utils.ToastUtil;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.yjb.R;
import com.yjb.mvp.contract.home.NewsViewIncContract;
import com.yjb.mvp.model.bean.NewsList;
import com.yjb.mvp.presenter.home.NVIcPresenter;

import butterknife.BindView;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

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
    @BindView(R.id.BBCC_webview)
    WebView BBCCWebview;

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
        tvNewsTime.setText("时间：" + DateFormatUtil.timeStamp2Date("" + newsData.getCreatetime()));
        tvNewsRead.setText(newsData.getView() + "已读");
        WebSettings webSettings = BBCCWebview.getSettings();

        /*与js交互*/
        webSettings.setJavaScriptEnabled(true);

        /*自适应屏幕*/
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        /*细节操作*/
        webSettings.setBuiltInZoomControls(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持js弹窗


        BBCCWebview.setWebViewClient(new WebViewClient());

        /**
         * 存储的html格式
         */
        String NOTICE_FORMAT = "" +
                "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<meta charset=\"UTF-8\"> " +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">" +
                "</head>" +
                "<body>" +
                "%s" +
                "</body>" +
                "</html> ";

        String ht = String.format(NOTICE_FORMAT, newsData.getContent());

        BBCCWebview.loadData(Html.fromHtml(ht).toString(), "text/html", "UTF-8");

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
